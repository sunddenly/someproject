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

idx_user=idx(user); %index of the group which the user belongs to�� ��ǰuser����cluster������
idx_original=find(idx==idx_user); %idex of users of this cluster in the original matriz ��cluster���û�����ԭʼ�����е�����
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
d_user=d(user,idx_original);%the cosine similarity between the our user with the  rest of the users in this cluster ��ǰ�û����cluster�������û����������ƶ�
[temp,temp1]=sort(d_user,'descend');   %find the nearest neighbor in this cluster of our usr �Եõ������ƶȽ������򣬵õ������knear���û�
if length(d_user)<knear
    knear=length(d_user);
end
sim=temp(1:knear); %similary value between our user and its neighbors of this cluster;sim���浱ǰ�û��������ھӵ����ƶ�
idx_vecino=idx_original(temp1(1:knear)); %the real idx of this neighbors;�õ���ǰ�û���k����ھ���ԭʼ�����е�����
idx_user_rated=find(data(:,item)~=0);%users who have been rated for this item; �ҵ���Ŀ���Ƽ���Ŀitem���������ֵ��û�
[idx_restOri,IdxRate,IdxSim]=intersect(idx_user_rated,idx_vecino);%the users of this cluster who have been rated for this item;��ø�cluster�ж�Ŀ���Ƽ���Ŀ���������ֵ��û�
if ~isempty(IdxSim)  %���cluster���е�ǰ�û�������ھӣ����������ھӵ����ּ���Ԥ������
    SimVecino=sim(IdxSim); %the similarity between the active user and its neighbors of this cluster;��õ�ǰ�û�������ھӵ����ƶ�
    RateItem=data(idx_restOri,item)';%ratings value of its neighbor of this cluster;�������ھӶ�Ŀ����Ŀ������
    PreUserItem=(SimVecino*RateItem')/sum(SimVecino);%��Ŀ����Ŀ����Ԥ������
end
%%
% determine whether other users of this cluster have rated for this item
if isempty(IdxSim) %���cluster��û�е�ǰ�û�������ھӣ���Ҫ�������û�Ⱥ��Ѱ�ҵ�ǰ�û�������ھ�
    
    D_ALLitem=d(user,1:length(data(:,1))); %similarity between all the users �������û������ƶ�
    [tempALL,temp1ALL]=sort(D_ALLitem,'descend');   %find the nearest neighbor �ҵ���ǰ�û�������ھ�
    IdxALL_vecino=temp1ALL(1:knear); %idx of neighbors 
    [ItemVecinoUser,IdxALLRate,IdxALLSim]=intersect(idx_user_rated,IdxALL_vecino);%users in these neighbors who have been rated this item �ҵ�����ھ��ж���Ŀ�������۵��û�
    if ~isempty(IdxALLSim) %�������ھ����ж�Ŀ����Ŀ���۹����û�
        SimVecino=D_ALLitem(ItemVecinoUser);
        RateItem=data(ItemVecinoUser,item)';%rating value of this item of those user who are also neighbor of the active user
        PreUserItem=(SimVecino*RateItem')/sum(SimVecino);
    end
    if isempty(IdxALLSim) %�������ھ���û�ж�Ŀ����Ŀ���۹����û�
        %if there is no set intersection of user neighbor set and users
        %who have voted for the active item
        DUserRated=D_ALLitem(idx_user_rated);  %similarity between all the users who voted for the active item and our active user �õ����ж�Ŀ����Ŀ���ֹ����û��뵱ǰ�û������ƶ�
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

