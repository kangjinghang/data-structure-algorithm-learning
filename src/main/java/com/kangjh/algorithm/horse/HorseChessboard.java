package com.kangjh.algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * 马踏棋盘算法
 *
 * @author kangjinghang
 * @version 1.0
 * @since 2019-08-12
 */
public class HorseChessboard {

    /**
     * 棋盘的行数
     */
    private static int X;

    /**
     * 棋盘的列数
     */
    private static int Y;

    /**
     * 创建一个数组，标记棋盘的各个位置是否被访问过
     */
    private static boolean[] visited;

    /**
     * 使用一个属性，标记是否棋盘的所有位置都被访问
     */
    private static boolean finished;

    public static void main(String[] args) {
        System.out.println("骑士周游算法，开始运行~~");
        X = 8;
        Y = 8;
        //马儿初始位置的行，从1 开始编号
        int row = 1;
        //马儿初始位置的列，从1 开始编号
        int column = 1;
        //创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时: " + (end - start) + " 毫秒");
        //输出棋盘的最后情况
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 完成骑士周游问题的算法
     *
     * @param chessboard 棋盘
     * @param row        马儿当前的位置的行从0 开始
     * @param column     马儿当前的位置的列从0 开始
     * @param step       是第几步,初始位置就是第1 步
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        //row = 4 X = 8 column = 4 = 4 * 8 + 4 = 36
        //标记该位置已经访问
        visited[row * X + column] = true;
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        //对ps 进行排序,排序的规则就是对ps 的所有的Point 对象的下一步的位置的数目，进行非递减排序
        sort(ps);
        //遍历ps
        while (!ps.isEmpty()) {
            //取出下一个可以走的位置
            Point p = ps.remove(0);
            //判断该点是否已经访问过
            if (!visited[p.y * X + p.x]) {
                //说明还没有访问过
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }
        //判断马儿是否完成了任务，使用step 和应该走的步数比较，
        //如果没有达到数量，则表示没有完成任务，将整个棋盘置0
        //说明: step < X * Y 成立的情况有两种
        //1. 棋盘到目前位置,仍然没有走完
        //2. 棋盘处于一个回溯过程
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    public static ArrayList<Point> next(Point curPoint) {
        //创建一个ArrayList
        ArrayList<Point> ps = new ArrayList<>();
        //创建一个Point
        Point p1 = new Point();
        //表示马儿可以走5 这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走6 这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走7 这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走0 这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走1 这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走2 这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走3 这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走4 这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    /**
     * 根据当前这个一步的所有的下一步的选择位置，进行非递减排序, 减少回溯的次数
     */
    public static void sort(ArrayList<Point> ps) {
        ps.sort(Comparator.comparingInt(o -> next(o).size()));
    }

}
