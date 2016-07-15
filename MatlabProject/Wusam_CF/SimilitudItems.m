function [D]=SimilitudItems(data,method)
%INPUT:
%data: rows should be observations while colunms will be variables
%method: metric
%copyright (c) 2013 WUSAM.
%wuxin.software@gmail.com

switch lower(method)
    case 'ga.train'
        D=pdist(data','cosine');
        D=squareform(D);
        D=1-D;
        for i=1:size(data,2)
            D(i,i)=0;
        end
    case 'correlation'
        for i=1:size(data,2)
            for j=1:size(data,2)
                temp=find(data(:,i)~=0 & data(:,j)~=0);
                Rui=data(temp,i);
                Ruj=data(temp,j);
                Ri=mean(data(:,i));
                Rj=mean(data(:,j));
                D(i,j)=(Rui-Ri)'*(Ruj-Rj)/(norm(Rui-Ri)*norm(Ruj-Rj));
                if isnan(D(i,j))
                    D(i,j)=0;
                end
                D(i,j)=abs(D(i,j));
                D(i,i)=0;
            end
        end
    case 'adjustedcosine'
        for i=1:size(data,2)
            for j=1:size(data,2)
                temp=find(data(:,i)~=0 & data(:,j)~=0);
                Rui=data(temp,i);
                Ruj=data(temp,j);
                Ru=mean(data(temp,:)')';
                D(i,j)=(Rui-Ru)'*(Ruj-Ru)/(norm(Rui-Ru)*norm(Ruj-Ru));
                if isnan(D(i,j))
                    D(i,j)=0;
                end
                D(i,j)=abs(D(i,j));
                D(i,i)=0;
            end
        end
end
                
                
            