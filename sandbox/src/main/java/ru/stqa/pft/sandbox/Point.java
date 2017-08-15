package ru.stqa.pft.sandbox;

public class Point {
  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Вычисление и вывод на экран расстояния между двумя точками на плоскости
   * @param p1 - координаты первой точки
   * @param p2 - координаты второй точки
   */
  public static void distance(Point p1, Point p2) {
    double result = Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    System.out.println("Расстояние между двумя точками с координатами (" + p1.x + "; " + p1.y + ") и (" + p2.x + " ;" + p2.y + ") = " + result);
  }
}
