package ru.stqa.pft.sandbox;

public class Point {
  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Вычисление расстояния до точки на плоскости
   * Например: A.distance(B) - расстояние от точки A до точки B
   *
   * @param p - объект класса Point, содержащий координаты второй точки
   */
  public double distance(Point p) {
    return Math.sqrt(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2));
  }
}
