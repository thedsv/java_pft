package ru.stqa.pft.sandbox;

import static ru.stqa.pft.sandbox.Point.distance;

public class MySecondProgram {

  public static void main(String[] args) {
    Point A = new Point(5, -3);
    Point B = new Point(-7, 2.3);
    Point C = new Point(-3.5, 9);
    Point D = new Point(0, 8);


    distance(A,B);
    distance(B,C);
    distance(C,D);
  }
}
