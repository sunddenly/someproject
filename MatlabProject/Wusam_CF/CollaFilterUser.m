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
%copyright (c) 2013 WUSAM.
%wuxin.software@gmail.com

idx_user=idx(user); %index of the group which the user belongs to， 当前user所属cluster的索引
idx_original=find(idx==idx_user); %idex of users of this cluster in the original matriz 该cluster中用户们在原始矩阵中的索引
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
mmmm=d
d_user=d(user,idx_original);%the cosine similarity between the our user with the  rest of the users in this cluster 求当前用户与该cluster中其他用户的余弦相似度
[temp,temp1]=sort(d_user,'descend');   %find the nearest neighbor in this cluster of our usr 对得到的相似度进行排序，得到最近的knear个用户
if length(d_user)<knear
    knear=length(d_user);
end
sim=temp(1:knear); %similary value between our user and its neighbors of this cluster;sim保存当前用户与其他邻居的相似度
idx_vecino=idx_original(temp1(1:knear)); %the real idx of this neighbors;得到当前用户的k最近邻居在原始矩阵中的索引
idx_user_rated=find(data(:,item)~=0);%users who have been rated for this item; 找到对目标推荐项目item做出过评分的用户
[idx_restOri,IdxRate,IdxSim]=intersect(idx_user_rated,idx_vecino);%the users of this cluster who have been rated for this item;获得该cluster中对目标推荐项目做出过评分的用户
if ~isempty(IdxSim)  %如果cluster中有当前用户的最近邻居，则根据最近邻居的评分计算预测评分
    SimVecino=sim(IdxSim); %the similarity between the active user and its neighbors of this cluster;获得当前用户与最近邻居的相似度
    RateItem=data(idx_restOri,item)';%ratings value of its neighbor of this cluster;获得最近邻居对目标项目的评分
    PreUserItem=(SimVecino*RateItem')/sum(SimVecino);%对目标项目做出预测评分
end
%%
% determine whether other users of this cluster have rated for this item
if isempty(IdxSim) %如果cluster中没有当前用户的最近邻居，需要从整个用户群中寻找当前用户的最近邻居
    
    D_ALLitem=d(user,1:length(data(:,1))); %similarity between all the users 与所有用户的相似度
    [tempALL,temp1ALL]=sort(D_ALLitem,'descend');   %find the nearest neighbor 找到当前用户的最近邻居
    IdxALL_vecino=temp1ALL(1:knear); %idx of neighbors 
    [ItemVecinoUser,IdxALLRate,IdxALLSim]=intersect(idx_user_rated,IdxALL_vecino);%users in these neighbors who have been rated this item 找到最近邻居中对项目做了评价的用户
    if ~isempty(IdxALLSim) %如果最近邻居中有对目标项目评价过的用户
        SimVecino=D_ALLitem(ItemVecinoUser);
        RateItem=data(ItemVecinoUser,item)';%rating value of this item of those user who are also neighbor of the active user
        PreUserItem=(SimVecino*RateItem')/sum(SimVecino);
    end
    if isempty(IdxALLSim) %如果最近邻居中没有对目标项目评价过的用户
        %if there is no set intersection of user neighbor set and users
        %who have voted for the active item
        DUserRated=D_ALLitem(idx_user_rated);  %similarity between all the users who voted for the active item and our active user 得到所有对目标项目评分过的用户与当前用户的相似度
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

