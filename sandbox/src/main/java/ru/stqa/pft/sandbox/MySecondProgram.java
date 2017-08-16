package ru.stqa.pft.sandbox;

public class MySecondProgram {

  public static void main(String[] args) {
    Point A = new Point(2, 4);
    Point B = new Point(7, 16);

    System.out.println("Расстояние между двумя точками с координатами (" + A.x + "; " + A.y + ") и (" + B.x + " ;" + B.y + ") = " + A.distance(B));
  }
}
