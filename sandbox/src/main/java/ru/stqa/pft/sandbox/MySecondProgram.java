package ru.stqa.pft.sandbox;

import static ru.stqa.pft.sandbox.Point.distance;

public class MySecondProgram {

  public static void main(String[] args) {
    Point A = new Point(5, -3);
    Point B = new Point(-7, 2);

    System.out.println("Расстояние между двумя точками A(" + A.x + "; " + A.y + ") и B(" + B.x + " ;" + B.y + ") = " + distance(A, B));
  }

}
