function [PreUserItem]=CollaFilterUser(data,item,user,knear,Kcluster,idx,method)

%INPUT:
%data: matriz of user-item
%item: idx of the item, for example, when it is 15, it means the 15th item
%user: idx of the user, for example, when it takes 5, it means the 5th user
%knear: number of neigbors
%Kcluster: number of clusters
%idx: idx of clusters
%method: similarity metric
%OUTPUT:
%PreUserItem: the predicted rating
%%
%copyright (c) 2010 CONCHA.
%concha.gong@gmail.com

idx_user=idx(user); %index of the group which the user belongs to
idx_original=find(idx==idx_user); %idex of users of this cluster in the original matriz
%load similarity matriz of users
switch lower(method)
    case 'cosine'
        load('SimilitudCosineUser.mat');
    case 'correlation'
        load('SimilitudCorrelationUser');
    case 'adjustedcosine'
        load('SimilitudAdjustedCosineUser');
end
%cluster
d_user=d(user,idx_original);%the cosine similarity between the our user with the  rest of the users in this cluster
[temp,temp1]=sort(d_user,'descend');   %find the nearest neighbor in this cluster of our usr
if length(d_user)<knear
    knear=length(d_user);
end
sim=temp(1:knear); %similary value between our user and its neighbors of this cluster
idx_vecino=idx_original(temp1(1:knear)); %the real idx of this neighbors
idx_user_rated=find(data(:,item)~=0);%users who have been rated for this item
[idx_restOri,IdxRate,IdxSim]=intersect(idx_user_rated,idx_vecino);%the users of this cluster who have been rated for this item
if ~isempty(IdxSim)
    SimVecino=sim(IdxSim); %the similarity between the active user and its neighbors of this cluster
    RateItem=data(idx_restOri,item)';%ratings value of its neighbor of this cluster
    PreUserItem=(SimVecino*RateItem')/sum(SimVecino);
end
%%
% determine whether other users of this cluster have rated for this item
if isempty(IdxSim)
    D_ALLitem=d(user,1:end); %similarity between all the users
    [tempALL,temp1ALL]=sort(D_ALLitem,'descend');   %find the nearest neighbor
    IdxALL_vecino=temp1ALL(1:knear); %idx of neighbors
    [ItemVecinoUser,IdxALLRate,IdxALLSim]=intersect(idx_user_rated,IdxALL_vecino);%users in these neighbors who have been rated this item
    if ~isempty(IdxALLSim)
        SimVecino=D_ALLitem(ItemVecinoUser);
        RateItem=data(ItemVecinoUser,item)';%rating value of this item of those user who are also neighbor of the active user
        PreUserItem=(SimVecino*RateItem')/sum(SimVecino);
    end
    if isempty(IdxALLSim)
        %if there is no set intersection of user neighbor set and users
        %who have voted for the active item
        DUserRated=D_ALLitem(idx_user_rated);  %similarity between all the users who voted for the active item and our active user
        [UserRated,IdxUserRated]=sort(DUserRated,'descend');  % rearrange the above similarity
        IdxUserRated=idx_user_rated(IdxUserRated); % real idx of the rearranged users
        if length(idx_user_rated)<knear
            knear=length(idx_user_rated);
        end
        SimVecino=UserRated(1:knear);
        RateItem=data(IdxUserRated(1:knear),item)';%ratings about the active ite:m of those users with maximum similarity
        PreUserItem=(SimVecino*RateItem')/sum(SimVecino);
    end
end

