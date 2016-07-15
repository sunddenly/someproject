clear; clc
CosineUser=[];
CosineItem=[];
CorrelaUser=[];
CorrelaItem=[];
AdjustUser=[];
AdjustItem=[];

for m=1:6
   s=['results', int2str(m)];
   load(s)
for i=1:12 %number of neighbors
    for j=1:6%number of groups
       Cosinetemp(j)=Mae_cosine{i,j}.UserBased;
       Cosinetemp1(j)=Mae_cosine{i,j}.ItemBased;
       Cosinetemp2(j)=MaeFix_cosine{i,j}.UserBased;
       Cosinetemp3(j)=MaeFix_cosine{i,j}.ItemBased;
       
       Correltemp(j)=Mae_correla{i,j}.UserBased;
       Correltemp1(j)=Mae_correla{i,j}.ItemBased;
       Correltemp2(j)=MaeFix_correla{i,j}.UserBased;
       Correltemp3(j)=MaeFix_correla{i,j}.ItemBased;
       
       Adjustemp(j)=Mae_adjusted{i,j}.UserBased;
       Adjustemp1(j)=Mae_adjusted{i,j}.ItemBased;
       Adjustemp2(j)=MaeFix_adjusted{i,j}.UserBased;
       Adjustemp3(j)=MaeFix_adjusted{i,j}.ItemBased;
       
    end
    MaeCosineUser(i,:)=Cosinetemp;
    MaeCosineItem(i,:)=Cosinetemp1;
    MaeCosineUserFix(i,:)=Cosinetemp2;
    MaeCosineItemFix(i,:)=Cosinetemp3;    
    
    MaeCorrelaUser(i,:)=Correltemp;
    MaeCorrelaItem(i,:)=Correltemp1;
    MaeCorrelaUserFix(i,:)=Correltemp2;
    MaeCorrelaItemFix(i,:)=Correltemp3;    

     MaeAdjustUser(i,:)=Adjustemp;
    MaeAdjustItem(i,:)=Adjustemp1;
    MaeAdjustUserFix(i,:)=Adjustemp2;
    MaeAdjustItemFix(i,:)=Adjustemp3;      
end
clear Cosinetemp Cosinetemp1 Cosinetemp2 Cosinetemp3
clear   Correltemp Correltemp1 Correltemp2 Correltemp3
clear Adjustemp Adjustemp1 Adjustemp2 Adjustemp3
clear Mae_adjusted Mae_correla Mae_cosine
clear MaeFix_adjusted MaeFix_correla MaeFix_cosine i j
 cosine(m,:)=reshape([MaeCosineItem MaeCosineUser],1,[]);
 correla(m,:)=reshape([MaeCorrelaItem MaeCorrelaUser],1,[]);
 adjust(m,:)=reshape([MaeAdjustItem MaeAdjustUser],1,[]);
 
 %combining all the experimente results
 CosineUser=[CosineUser;MaeCosineUser];
 CosineItem=[CosineItem;MaeCosineItem];
 CorrelaUser=[CorrelaUser; MaeCorrelaUser];
 CorrelaItem=[CorrelaItem; MaeCorrelaItem];
 AdjustUser=[AdjustUser; MaeAdjustUser];
 AdjustItem=[AdjustItem; MaeAdjustItem];
 end
cosine=reshape(cosine,1,[]);%all the experimental results for cosine
correla=reshape(correla,1,[]);
adjust=reshape(adjust,1,[]);
%% for cosine

knear=[5 10 20 30 40 50 60 80 100 130 160 200];
kcluster=[5 10 20 30 40 50];
 %for cosine
 options={'-+y', '-om','-*c','-xr','-sg','-db'};
 color={'y','m','c','r','g','b'};
 for i=1:length(kcluster)
     clear temp temp1
     for j=1:length(knear)
         temp(j,:)=CosineUser(j:12:60+j,i);
         temp1(j,:)=CosineItem(j:12:60+j,i);
     end
     figure(1)
     plot(knear,nanmean(temp'),options{i},'markersize',8,'MarkerFaceColor',color{i});
     grid on
     hold on
     h= legend('No.Clusters=5','No.Clusters=10','No.Clusters=20','No.Clusters=30','No.Clusters=40','No.Clusters=50');
    set(h,'fontsize',18,'fontweight','b');
     ylabel('MAE','fontsize',18,'fontweight','b'); xlabel('NO. neighbors','fontsize',18,'fontweight','b');
     title('UserBased & Cosine','fontsize',18,'fontweight','b');
     figure(2)
     plot(knear,nanmean(temp1'),options{i},'markersize',8,'MarkerFaceColor',color{i});
     grid on
     hold on
     h=legend('No.Clusters=5','No.Clusters=10','No.Clusters=20','No.Clusters=30','No.Clusters=40','No.Clusters=50');
     set(h,'fontsize',18,'fontweight','b');
     ylabel('MAE','fontsize',18,'fontweight','b'); xlabel('NO. neighbors','fontsize',18,'fontweight','b');
     title('ItemBased & Cosine','fontsize',18,'fontweight','b');    
 end
 
 %from figure 1 and figure 2
 clear temp temp1
 for j=1:length(knear)
     temp(j,:)=CosineUser(j:12:60+j,1);
     temp1(j,:)=CosineItem(j:12:60+j,1);
 end
 figure(3)
 plot(knear,nanmean(temp'),'-dr','markersize',8,'MarkerFaceColor','r');
 hold on
 plot(knear,nanmean(temp1'),'-ob','markersize',8,'MarkerFaceColor','b');
 grid on
 h=legend('UserBased', 'ItemBased');
 set(h,'fontsize',18,'fontweight','b');
 ylabel('MAE','fontsize',18,'fontweight','b'); xlabel('NO. neighbors','fontsize',18,'fontweight','b');
 title('ItemBased VS UserBased (NO.Cluster=5, COSINE)','fontsize',18,'fontweight','b');
 
 %% for correlation
 
 knear=[5 10 20 30 40 50 60 80 100 130 160 200];
kcluster=[5 10 20 30 40 50];

 options={'-+y', '-om','-*c','-xr','-sg','-db'};
 color={'y','m','c','r','g','b'};
 for i=1:length(kcluster)
     clear temp temp1
     for j=1:length(knear)
         temp(j,:)=CorrelaUser(j:12:60+j,i);
         temp1(j,:)=CorrelaItem(j:12:60+j,i);
     end
     figure(4)
     plot(knear,nanmean(temp'),options{i},'markersize',8,'MarkerFaceColor',color{i});
     grid on
     hold on
     h= legend('No.Clusters=5','No.Clusters=10','No.Clusters=20','No.Clusters=30','No.Clusters=40','No.Clusters=50');
    set(h,'fontsize',18,'fontweight','b');
     ylabel('MAE','fontsize',18,'fontweight','b'); xlabel('NO. neighbors','fontsize',18,'fontweight','b');
     title('UserBased & Correlation','fontsize',18,'fontweight','b');
     figure(5)
     plot(knear,nanmean(temp1'),options{i},'markersize',8,'MarkerFaceColor',color{i});
     grid on
     hold on
     h=legend('No.Clusters=5','No.Clusters=10','No.Clusters=20','No.Clusters=30','No.Clusters=40','No.Clusters=50');
     set(h,'fontsize',18,'fontweight','b');
     ylabel('MAE','fontsize',18,'fontweight','b'); xlabel('NO. neighbors','fontsize',18,'fontweight','b');
     title('ItemBased & Correlation','fontsize',18,'fontweight','b');    
 end
 
 %from figure 1 and figure 2
 clear temp temp1
 for j=1:length(knear)
     temp(j,:)=CorrelaUser(j:12:60+j,1);
     temp1(j,:)=CorrelaItem(j:12:60+j,6);
 end
 figure(6)
 plot(knear,nanmean(temp'),'-dr','markersize',8,'MarkerFaceColor','r');
 hold on
 plot(knear,nanmean(temp1'),'-ob','markersize',8,'MarkerFaceColor','b');
 grid on
 h=legend('UserBased, NO. cluster=5', 'ItemBased, NO.cluster=50');
 set(h,'fontsize',18,'fontweight','b');
 ylabel('MAE','fontsize',18,'fontweight','b'); xlabel('NO. neighbors','fontsize',18,'fontweight','b');
 title('ItemBased VS UserBased (CORRELATION)','fontsize',18,'fontweight','b');
 
 %% for adjustedcosine
  knear=[5 10 20 30 40 50 60 80 100 130 160 200];
kcluster=[5 10 20 30 40 50];

 options={'-+y', '-om','-*c','-xr','-sg','-db'};
 color={'y','m','c','r','g','b'};
 for i=1:length(kcluster)
     clear temp temp1
     for j=1:length(knear)
         temp(j,:)=AdjustUser(j:12:60+j,i);
         temp1(j,:)=AdjustItem(j:12:60+j,i);
     end
     figure(7)
     plot(knear,nanmean(temp'),options{i},'markersize',8,'MarkerFaceColor',color{i});
     grid on
     hold on
     h= legend('No.Clusters=5','No.Clusters=10','No.Clusters=20','No.Clusters=30','No.Clusters=40','No.Clusters=50');
    set(h,'fontsize',18,'fontweight','b');
     ylabel('MAE','fontsize',18,'fontweight','b'); xlabel('NO. neighbors','fontsize',18,'fontweight','b');
     title('UserBased & AdjustedCosine','fontsize',18,'fontweight','b');
     figure(8)
     plot(knear,nanmean(temp1'),options{i},'markersize',8,'MarkerFaceColor',color{i});
     grid on
     hold on
     h=legend('No.Clusters=5','No.Clusters=10','No.Clusters=20','No.Clusters=30','No.Clusters=40','No.Clusters=50');
     set(h,'fontsize',18,'fontweight','b');
     ylabel('MAE','fontsize',18,'fontweight','b'); xlabel('NO. neighbors','fontsize',18,'fontweight','b');
     title('ItemBased & AdjustedCosine','fontsize',18,'fontweight','b');    
 end
 
 %from figure 1 and figure 2
 clear temp temp1
 for j=1:length(knear)
     temp(j,:)=AdjustUser(j:12:60+j,1);
     temp1(j,:)=AdjustItem(j:12:60+j,6);
 end
 figure(9)
 plot(knear,nanmean(temp'),'-dr','markersize',8,'MarkerFaceColor','r');
 hold on
 plot(knear,nanmean(temp1'),'-ob','markersize',8,'MarkerFaceColor','b');
 grid on
 h=legend('UserBased, NO. cluster=5', 'ItemBased, NO.cluster=50');
 set(h,'fontsize',18,'fontweight','b');
 ylabel('MAE','fontsize',18,'fontweight','b'); xlabel('NO. neighbors','fontsize',18,'fontweight','b');
 title('ItemBased VS UserBased (ADJUSTEDCOSINE)','fontsize',18,'fontweight','b');
 
 %%
  clear temp temp1 temp2 temp3 temp4 temp5
 for j=1:length(knear)
     temp(j,:)=CosineUser(j:12:60+j,1);
     temp1(j,:)=CosineItem(j:12:60+j,1);
      temp2(j,:)=CorrelaUser(j:12:60+j,1);
     temp3(j,:)=CorrelaItem(j:12:60+j,6);
     temp4(j,:)=AdjustUser(j:12:60+j,1);
     temp5(j,:)=AdjustItem(j:12:60+j,5);   
 end
 figure(10)
 plot(knear,nanmean(temp1'),'-dr','markersize',8,'MarkerFaceColor','r');
 hold on; grid on
 plot(knear,nanmean(temp3'),'-ob','markersize',8,'MarkerFaceColor','b');
  plot(knear,nanmean(temp5'),'-og','markersize',8,'MarkerFaceColor','g');
 h=legend('COSINE', 'CORRELATION','ADJUSTEDCOSINE');
 set(h,'fontsize',18,'fontweight','b');
 ylabel('MAE','fontsize',18,'fontweight','b'); xlabel('NO. neighbors','fontsize',18,'fontweight','b');
 title('COSINE VS CORRELATION VS ADJUSTEDCOSINE (ItemBased)','fontsize',18,'fontweight','b');
 
 figure(11)
 plot(knear,nanmean(temp'),'-dr','markersize',8,'MarkerFaceColor','r');
 hold on; grid on
 plot(knear,nanmean(temp2'),'-ob','markersize',8,'MarkerFaceColor','b');
  plot(knear,nanmean(temp4'),'-og','markersize',8,'MarkerFaceColor','g');
 h=legend('COSINE', 'CORRELATION','ADJUSTEDCOSINE');
 set(h,'fontsize',18,'fontweight','b');
 ylabel('MAE','fontsize',18,'fontweight','b'); xlabel('NO. neighbors','fontsize',18,'fontweight','b');
 title('COSINE VS CORRELATION VS ADJUSTEDCOSINE (UserBased)','fontsize',18,'fontweight','b');


 
 


