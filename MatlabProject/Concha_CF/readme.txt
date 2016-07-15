数据保存在ga.mat文件中，其中ga为struct结构，即ga.train为训练集，ga.test
为测试集，我这里用的是movielens 数据集，在下载这个数据集的网站上面，已经将数据集
分为了测试集和训练集，我用的就是他们网上的这个版本，只不过是将他们存在了一个ga.mat
数据集里。
我们知道不论是基于项目还是基于用户的协同过滤，都需要计算相互之间的距离，或者说是相似度，
然后这个计算时off-line的，这意味着我们需要事先计算好，然后保存在.mat文件中，以备测试时
使用。这几个矩阵也非常大，所以我没办法传到论坛上，但是大家可以自己用SimilitudItems.m这个
函数来计算。

function [D]=SimilitudItems(data,method)

由于我在CollaFilter.m和CollaFilterUser.m函数都调用了这些距离矩阵：

        SimilitudCosine.mat %请将计算的项目之间的基于cosine的相似度矩阵保存成这个名字
        SimilitudCorrelation %请将计算的项目之间的基于correlation的相似度矩阵保存成这个名字
        SimilitudAdjustedCosine %请将计算的项目之间的基于Adjustedcosine的相似度矩阵保存成这个名字

和
        SimilitudCosineUser.mat; %请将计算的用户之间的基于cosine的相似度矩阵保存成这个名字
        SimilitudCorrelationUser; %请将计算的用户之间的基于correlation的相似度矩阵保存成这个名字
        SimilitudAdjustedCosineUser; %请将计算的用户之间的基于Adjustedcosine的相似度矩阵保存成这个名字

主要函数为CollaFilter.m 和 CollaFilterUser.m，这两个函数基本上每一行我都注释了（真是佩服我
自己第一次写代码这么详细，o(╯□╰)o），没有什么难的，就是一些index倒腾来倒腾去啦。
  
希望对大家有帮助。

CONCHA
concha.gong@gmail.com
