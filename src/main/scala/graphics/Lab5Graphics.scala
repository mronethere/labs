package graphics

import api.{LabController, LabData}
import core.Boot.system.dispatcher

import scala.concurrent.Future

object Lab5Graphics extends LabController {
  def solve(data: LabData): Future[List[String]] = Future {
    val params = data.params.map(_.toInt)
    buildPoints(params.head, params(1), params(2), params(3),
                params(4), params(5), params(6), params(7)) flatMap {
      case xy => List(xy._1.toString, xy._2.toString)
    }
  }


  def buildPoints(_x1: Int, _y1: Int, _x2: Int, _y2: Int,
                  _xMin: Int, _yMin: Int, _xMax: Int, _yMax: Int): List[(Int, Int)] = {
    def drawLine(x1: Int, y1: Int, x2: Int, y2: Int) = Lab1Graphics.buildPoints2(x1, y1, x2, y2)
    var list = List.empty[(Int, Int)]
    var (xs, ys, xe, ye) = (_x1, _y1, _x2, _y2)
    var (xMin, yMin, xMax, yMax) = (_xMin, _yMin, _xMax, _yMax)

    list ++= drawLine(xMin,yMin,xMin,yMax); list ++= drawLine(xMax,yMin,xMax,yMax)
    list ++= drawLine(xMin,yMin,xMax,yMin); list ++= drawLine(xMin,yMax,xMax,yMax)

    def top0() { xs= xs + (xe-xs)*(yMax-ys)/(ye-ys); ys= yMax }
    def bottom0() { xs= xs + (xe-xs)*(yMin-ys)/(ye-ys); ys= yMin }
    def right0() { ys= ys + (ye-ys)*(xMax-xs)/(xe-xs); xs= xMax }
    def left0() { ys= ys + (ye-ys)*(xMin-xs)/(xe-xs); xs= xMin }
    def top1() { xe= xe + (xs-xe)*(yMax-ye)/(ys-ye); ye= yMax }
    def bottom1() { xe= xe + (xs-xe)*(yMin-ye)/(ys-ye); ye= yMin }
    def right1() { ye= ye + (ys-ye)*(xMax-xe)/(xs-xe); xe= xMax }
    def left1() { ye= ye + (ys-ye)*(xMin-xe)/(xs-xe); xe= xMin }

    def FC(x0: Int, y0: Int,x1: Int, y1: Int) = {
      var code = 0; var visible= 0
      xs= x0; ys= y0; xe= x1; ye= y1
      if (ye > yMax) code+= 8 else if (ye < yMin) code+= 4
      else if (xe < xMin) code+= 1 else if (ys < yMin) code += 64
      if (xs > xMax) code+= 32 else if (xs < xMin) code+= 16
      code match {
        //centre
        case 0x00 => visible += 1
        case 0x01 => left1(); visible += 1
        case 0x02 => right1(); visible += 1
        case 0x04 => bottom1(); visible += 1
        case 0x05 => left1(); if (ye < yMax) bottom1(); visible += 1
        case 0x06 => right1(); if (ye < yMin) bottom1();visible += 1
        case 0x08 => top1(); visible += 1
        case 0x09 => left1();if (ye > yMax) top1(); visible += 1
        case 0x0A => right1();if (ye > yMax) top1(); visible += 1
        //left
        case 0x10 => left0(); visible += 1
        case 0x11 =>
        case 0x12 => left0(); right1(); visible += 1
        case 0x14 => left0();if (ys < yMin) bottom1(); visible += 1
        case 0x15 =>
        case 0x16 => left0();if (ys < yMin) bottom1();if (xe > xMax) right1(); visible += 1;
        case 0x18 => left0();if (ys > yMax) top1(); visible += 1
        case 0x19 =>
        case 0x1A => left0();if (ys > yMax) top1();if (xe > xMax) right1(); visible += 1;
        //right
        case 0x20 => right0(); visible += 1
        case 0x21 => right0(); left1(); visible += 1
        case 0x22 =>
        case 0x24 => right0();if (ys < yMin) bottom1(); visible += 1;
        case 0x25 => right0();if (ys < yMin) bottom1();if (xe < xMin) left1(); visible += 1
        case 0x26 =>
        case 0x28 => right0();if (ys > yMax) top1(); visible += 1;
        case 0x29 => right0();if (ys > yMax) top1();if (xe < xMin) left1(); visible += 1
        case 0x2A =>
        //down
        case 0x40 => bottom0(); visible += 1
        case 0x41 => bottom0();if (xs < xMin) left1();if (ye < yMin) bottom1(); visible += 1;
        case 0x42 => bottom0();if (xs > xMax) right1(); visible += 1
        case 0x44 =>
        case 0x45 =>
        case 0x46 =>
        case 0x48 => bottom0();top1(); visible += 1;
        case 0x49 => bottom0();if (xs < xMin) left1();if (ye > yMax) top1(); visible += 1;
        case 0x4A => bottom0();if (xs > xMax) right1();if (ye > yMax) top1(); visible += 1;
        //down-left
        case 0x50 => left0();if (ys < yMin) bottom0(); visible += 1
        case 0x51 =>
        case 0x52 => right1();if (ye < yMin) bottom0();if (xs < xMin) left0(); visible += 1
        case 0x54 =>
        case 0x55 =>
        case 0x56 =>
        case 0x58 => top1();if (xe < xMin) bottom0();if (xs < xMin) left0(); visible += 1
        case 0x59 =>
        case 0x5A => left0();if (ys > yMax) right1();if (ye < yMin) if (ys < yMin) bottom0();if (ye > yMax) top1(); visible += 1;
        //down-left
        case 0x60 => right0();if (ys < yMin) bottom0(); visible += 1;
        case 0x61 => left1();if (ye < yMin) bottom0();if (xs > xMax) right0(); visible += 1
        case 0x62 =>
        case 0x64 =>
        case 0x65 =>
        case 0x66 =>
        case 0x68 => top1();if (xe > xMax) right0();if (ys < yMin) bottom0(); visible += 1;
        case 0x69 => left1();if (ye < yMin) right0();if (ys > yMax) if (ye > yMax) top1();if (ys < yMin) bottom0(); visible += 1
        case 0x6A =>
        //up
        case 0x80 => top0(); visible += 1;
        case 0x81 => top0();if (xs < xMin) left1(); visible += 1;
        case 0x82 => top0();if (xs > xMax) right1(); visible += 1;
        case 0x84 => top0();bottom1(); visible += 1;
        case 0x85 => top0();if (xs < xMin) left1();if (ye < yMin) bottom1(); visible += 1;
        case 0x86 => top0();if (xs > xMax) right1();if (ye < yMin) bottom1(); visible += 1
        case 0x88 =>
        case 0x89 =>
        case 0x8A =>
        //up-left
        case 0x90 => left0();if (ys > yMax) top0(); visible += 1
        case 0x91 =>
        case 0x92 => right1();if (ye > yMax) top0();if (xs < xMin) left0(); visible += 1;
        case 0x94 => bottom1();if (xe < xMin) left0();if (ys > yMax) top0(); visible += 1
        case 0x95 =>
        case 0x96 => left0();if (ys < yMin) right1();if (ye > yMax) if (ys > yMax) top0();if (ye < yMin) bottom1(); visible += 1
        case 0x98 =>
        case 0x99 =>
        case 0x9A =>
        //up-right
        case 0xA0 => right0();if (ys > yMax) top0(); visible += 1;
        case 0xA1 => left1();if (ye > yMax) top0();if (xs > xMax) right0(); visible += 1
        case 0xA2 =>
        case 0xA4 => bottom1();if (xe > xMax) right0();if (ys > yMax) top0(); visible += 1;
        case 0xA5 => left1();if (ye > yMax) right0();if (ys < yMin) if (ye < yMin) bottom1();if (ys > yMax) top0(); visible += 1
        case 0xA6 =>
        case 0xA8 =>
        case 0xA9 =>
        case 0xAA =>
        case _ => visible = -1;
      }
      if (visible > 0) list ++ drawLine(xs, ys, xe, ye)
      if (visible == 1)  list ++ drawLine(x0, y0, x1, y1)
    }
    FC(_x1, _y1, _x2, _y2)
    list
  }
}
