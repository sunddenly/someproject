%copyright (c) 2010 CONCHA.
%concha.gong@gmail.com

clear clc
zz={'results8','results9'};
for z=1:2
load ga
%remove those users who have voted less than 20 items
[M,N]=size(ga.train);
for i=1:M
    Vote(i)=length(nonzeros(ga.train(i,:)));
end
IdxMenor=find(Vote<20);
[ga.train,ps]=removerows(ga.train,IdxMenor);
[ga.test,ps]=removerows(ga.test,IdxMenor);
clear i M N ps IdxMenor Vote

%remove the all-zero vector
for i=1:size(ga.train,2)
    num_zero(i)=sum(ga.train(:,i));
end
idx_zero=find(num_zero==0);
ga.train=removerows(ga.train',idx_zero);
ga.train=ga.train';
clear i num_zero idx_zero

knear=[5 10 20 30 40 50 60 80 100 130 160 200];
kcluster=[5 10 20 30 40 50];
[M,N]=size(ga.test);
NoUser=50;
randUser=randperm(M);
clear M N 
%for cosine similarity 
for i=1:length(knear)
    for j=1:length(kcluster)
        fprintf('knear = %d, kcluster = %d \n', [knear(i), kcluster(j)])
        [Mae_cosine{i,j},MaeFix_cosine{i,j}]=CF(ga,knear(i),kcluster(j),'cosine',randUser,NoUser);
%      [Mae,MaeFix]=CF(ga,knear,Kcluster,method,randUser,NoUser)
        %knear:  number of neigbor
        %Kcluster: number of groups
        %method: similarity method, it can be 'cosine', 'correlation','adjustedcosine'
        %randUser: idx of randomly choosed  users
        %NoUser: number of randomly choosed users
    end
end

%for correlation similarity 
disp('FOR CORRELATION SIMILARITY')
for i=1:length(knear)
    for j=1:length(kcluster)
        fprintf('knear = %d, kcluster = %d \n', [knear(i), kcluster(j)])
        [Mae_correla{i,j},MaeFix_correla{i,j}]=CF(ga,knear(i),kcluster(j),'correlation',randUser,NoUser);
    end
end


%for adjustedcosine similarity 
disp('FOR ADJUSTEDCOSINE SIMILARITY')
for i=1:length(knear)
    for j=1:length(kcluster)
        fprintf('knear = %d, kcluster = %d \n', [knear(i), kcluster(j)])
        [Mae_adjusted{i,j},MaeFix_adjusted{i,j}]=CF(ga,knear(i),kcluster(j),'adjustedcosine',randUser,NoUser);
    end
end
clear NoUser i j knear kcluster randUser ga ans

save(zz{z}, 'Mae_cosine', 'MaeFix_cosine', 'Mae_correla', 'MaeFix_correla', ...
    'Mae_adjusted', 'MaeFix_adjusted')
end