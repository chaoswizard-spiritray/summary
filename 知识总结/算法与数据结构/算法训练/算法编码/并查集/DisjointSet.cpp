#include<iostream>
using namespace std;
/****
普通并查集模板，包括：初始化集合，查找根节点，合并两个节点，判断并压缩树的高度
*/
//定义全局数组
#define MAXSIZE 3000
int set[MAXSIZE];
int height[MAXSIZE];
//初始化
void inint(int[] a,int n) {
	for(int i=0; i<n; i++) {
		a[i]=-1;//初始化为-1表示这些节点都是独立的，不属于任何一个集合。
	}
}

/*递归查找根节点，问题1：假如我们的集合本身就是数目不多，且不用寻找连通图，我们一定要使用这种代价极高的递归查找吗？
  为何不使用少量的数值标识，代表集合即可？
  问题2：一个简单int型能够满足复杂关系的合并吗？不适用结构体？
*/
int findRoot(int child) {
	if(set[child]!=-1) {
		return findRoot(set[child]);
	} else {
		return child;
	}
}
/*合并两个节点,首先合并就是两个节点之间由一定关系从而将他们合并为同一个集合。所以并查集的主要处理逻辑就是这里
注意：
第一：我们的关系可能有很多，如何合并，哪些合并？

*/
//这里我们是判断树中是否有环
void unionPoint(int point1,int point2) {
	//先查找各自的根节点
	int root1=findRoot(point1);
	int root2=findRoot(point2);
	//判断各自的根节点是否相等，如果相等，我们就退出，否则我们就将他们归为一个集合
	if(root1==root2) {
		cout<<"树中有环";
	} else {
		//因为以树的结构进行构造，所以我们需要使用指定其中一个点为根,为了优化，我们比较各自树的高度，以高度低的为根
		//所以我们需要添加记录每一个树高的全局变量，最初为每一个节点为一个树，所以我们需要一个数组进行存储树高
		if(height[root1]<height[root2]) {
			//将根节点1作为新根
			set[root2]=root1;
			//高度加1
			height[root1]++;
		} else {
			//将根节点2作为新根
			set[root1]=root2;
			//高度加1
			height[root2]++;
		}

	}
}
int main() {

	return 0;
}
