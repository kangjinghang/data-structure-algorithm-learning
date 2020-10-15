package com.kangjh.algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * HorseChessboard2
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2020-08-24
 * @since 1.0.0
 */
public class HorseChessboard2 {

    private static int X;

    private static int Y;

    private static boolean[][] visited;

    private static boolean finished;

    public static void main(String[] args) {
        System.out.println("骑士周游算法，开始运行~~");
        X = 8;
        Y = 8;
        int row = 1;
        int column = 1;
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X][Y];
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时: " + (end - start) + " 毫秒");
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        visited[row][column] = true;
        ArrayList<Point> ps = next(new Point(column, row));
//        sort(ps);
        while (!ps.isEmpty()) {
            Point next = ps.remove(0);
            if (!visited[next.y][next.x]) {
                traversalChessboard(chessboard, next.y, next.x, step + 1);
            }
        }
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row][column] = false;
        } else {
            finished = true;
        }
    }


    public static ArrayList<Point> next(Point curr) {
        ArrayList<Point> ps = new ArrayList<>();
        Point next = new Point();
//        if ((next.x = curr.x - 2) >= 0 && (next.y = curr.y - 1) >= 0) {
//            ps.add(new Point(next));
//        }
//        if ((next.x = curr.x - 1) >= 0 && (next.y = curr.y - 2) >= 0) {
//            ps.add(new Point(next));
//        }
//        if ((next.x = curr.x + 1) < X && (next.y = curr.y - 2) >= 0) {
//            ps.add(new Point(next));
//        }
        if ((next.x = curr.x + 2) < X && (next.y = curr.y - 1) >= 0) {
            ps.add(new Point(next));
        }
        if ((next.x = curr.x + 2) < X && (next.y = curr.y + 1) < Y) {
            ps.add(new Point(next));
        }
        if ((next.x = curr.x + 1) < X && (next.y = curr.y + 2) < Y) {
            ps.add(new Point(next));
        }
        if ((next.x = curr.x - 1) >= 0 && (next.y = curr.y + 2) < Y) {
            ps.add(new Point(next));
        }
        if ((next.x = curr.x - 2) >= 0 && (next.y = curr.y + 1) < Y) {
            ps.add(new Point(next));
        }
        if ((next.x = curr.x - 2) >= 0 && (next.y = curr.y - 1) >= 0) {
            ps.add(new Point(next));
        }
        if ((next.x = curr.x - 1) >= 0 && (next.y = curr.y - 2) >= 0) {
            ps.add(new Point(next));
        }
        if ((next.x = curr.x + 1) < X && (next.y = curr.y - 2) >= 0) {
            ps.add(new Point(next));
        }
        return ps;
    }

    public static void sort(ArrayList<Point> ps) {
        ps.sort(Comparator.comparingInt(o -> next(o).size()));
    }

}
