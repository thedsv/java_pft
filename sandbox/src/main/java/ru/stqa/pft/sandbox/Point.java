package ru.stqa.pft.sandbox;

public class Point {
  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Вычисление и вывод на экран расстояния до точки на плоскости
   * Например: A.distance(B) - расстояние от точки A до точки B
   *
   * @param p - объект класса Point, содержащий координаты второй точки
   */
  public void distance(Point p) {
    double result = Math.sqrt(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2));
    System.out.println("Расстояние между двумя точками с координатами (" + this.x + "; " + this.y + ") и (" + p.x + " ;" + p.y + ") = " + result);
  }
}
