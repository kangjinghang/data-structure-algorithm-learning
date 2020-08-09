package com.kangjh.algorithm.prim;

import java.util.Arrays;

/**
 * 普利姆算法
 *
 * @author kangjinghang
 * @version 1.0
 * @since 2019-08-12
 */
public class PrimAlgorithm {

    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertexs = data.length;
        //邻接矩阵的关系使用二维数组表示,10000 这个大数，表示两个点不联通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}};
        //创建MGraph 对象
        MGraph graph = new MGraph(vertexs);
        //创建一个MinTree 对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph, vertexs, data, weight);
        minTree.showGraph(graph);
        minTree.prim(graph, 1);
    }

}

/**
 * 创建最小生成树->村庄的图
 */
class MinTree {

    /**
     * 创建图的邻接矩阵
     *
     * @param graph   图对象
     * @param vertexs 图对应的顶点个数
     * @param data    图的各个顶点的值
     * @param weight  图的邻接矩阵
     */
    public void createGraph(MGraph graph, int vertexs, char data[], int[][] weight) {
        int i, j;
        for (i = 0; i < vertexs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < vertexs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    /**
     * 显示图的邻接矩阵
     */
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * 编写prim 算法，得到最小生成树
     *
     * @param graph 图
     * @param v     表示从图的第几个顶点开始生成'A'->0 'B'->1...
     */
    public void prim(MGraph graph, int v) {
        //visited[] 标记结点(顶点)是否被访问过
        //visited[] 默认元素的值都是0, 表示没有访问过
        int visited[] = new int[graph.vertexs];
        //把当前这个结点标记为已访问
        visited[v] = 1;
        //h1 和h2 记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        //将minWeight 初始成一个大数，后面在遍历过程中，会被替换
        int minWeight = 10000;
        //因为有graph.verxs 顶点，普利姆算法结束后，有graph.verxs-1 边
        for (int k = 1; k < graph.vertexs; k++) {
            //这个是确定每一次生成的子图，和哪个结点的距离最近
            // i 结点表示被访问过的结点
            for (int i = 0; i < graph.vertexs; i++) {
                //j 结点表示还没有访问过的结点
                for (int j = 0; j < graph.vertexs; j++) {
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        //替换minWeight(寻找已经访问过的结点和未访问过的结点间的权值最小的边)
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条边是最小
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + minWeight);
            //将当前这个结点标记为已经访问
            visited[h2] = 1;
            //minWeight 重新设置为最大值10000
            minWeight = 10000;
        }
    }

}

class MGraph {

    /**
     * 表示图的节点个数
     */
    int vertexs;

    /**
     * 存放结点数据
     */
    char[] data;

    /**
     * 存放边，就是我们的邻接矩阵
     */
    int[][] weight;

    public MGraph(int vertexs) {
        this.vertexs = vertexs;
        data = new char[vertexs];
        weight = new int[vertexs][vertexs];
    }

}
