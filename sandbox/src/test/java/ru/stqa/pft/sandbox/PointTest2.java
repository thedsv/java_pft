package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest2 {

  @Test
  public void testDistance() {
    Point A = new Point(2, 4);
    Point B = new Point(7, 16);

    Assert.assertTrue(A.distance(B) > 10, "Расстояние между точками меньше 10");
  }
}
