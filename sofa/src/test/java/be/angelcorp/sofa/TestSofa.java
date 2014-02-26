package be.angelcorp.sofa;

import junit.framework.Assert;
import org.bridj.Pointer;
import org.junit.Test;

import static be.angelcorp.sofa.SofaLibrary.*;

/*
** Manual port of the t_sofa_c.c source file to ensure that the java port produces the same valid results.
**
**  - - - - - - - - -
**   t _ s o f a _ c
**  - - - - - - - - -
**
**  Validate the SOFA C functions.
**
**  Each SOFA function is at least called and a usually quite basic test
**  is performed.  Successful completion is signalled by a confirming
**  message.  Failure of a given function or group of functions results
**  in error messages.
**
**  All messages go to stdout.
**
**  This revision:  2012 February 23
**
**  SOFA release 2012-03-01
**
**  Copyright (C) 2012 IAU SOFA Board.  See notes at end.
*/
public class TestSofa {

    static public void assertEquals(Pointer<Byte> expected, char actual) {
        Assert.assertEquals(expected.get().byteValue(), (byte)actual);
    }
    static public void assertEquals(Byte expected, char actual) {
        Assert.assertEquals(expected.byteValue(), (byte)actual);
    }
    static public void assertEquals(Pointer<Integer> expected, int actual) {
        Assert.assertEquals(expected.get().intValue(), actual);
    }
    static public void assertEquals(Integer expected, int actual) {
        Assert.assertEquals(expected.intValue(), actual);
    }
    static public void assertEquals(Pointer<Double> expected, double actual) {
        Assert.assertEquals(expected.get().doubleValue(), actual);
    }
    static public void assertEquals(Pointer<Double> expected, double actual, double tol) {
        Assert.assertEquals(expected.get().doubleValue(), actual, tol);
    }
    static public void assertEquals(Double expected, double actual) {
        Assert.assertEquals(expected.doubleValue(), actual);
    }
    static public void assertEquals(Double expected, double actual, double tol) {
        Assert.assertEquals(expected.doubleValue(), actual, tol);
    }

    /*
    **  - - - - - - -
    **   t _ a 2 a f
    **  - - - - - - -
    **
    **  Test iauA2af function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauA2af, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_a2af() {
        Pointer<Integer> idmsf = Pointer.allocateInts(4);
        Pointer<Byte>    s     = Pointer.allocateByte();

        SofaLibrary.iauA2af(4, 2.345, s, idmsf);

        assertEquals(s, '+' );

        assertEquals(idmsf.get(0),  134);
        assertEquals(idmsf.get(1),   21);
        assertEquals(idmsf.get(2),   30);
        assertEquals(idmsf.get(3), 9706);
    }

    /*
    **  - - - - - - -
    **   t _ a 2 t f
    **  - - - - - - -
    **
    **  Test iauA2tf function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauA2tf, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_a2tf() {
        Pointer<Integer> ihmsf = Pointer.allocateInts(4);
        Pointer<Byte>    s     = Pointer.allocateByte();

        iauA2tf(4, -3.01234, s, ihmsf);

        assertEquals(s, '-');

        assertEquals(ihmsf.get(0),   11);
        assertEquals(ihmsf.get(1),   30);
        assertEquals(ihmsf.get(2),   22);
        assertEquals(ihmsf.get(3), 6484);
    }

    /*
    **  - - - - - - -
    **   t _ a f 2 a
    **  - - - - - - -
    **
    **  Test iauAf2a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauAf2a, assertEquals
    **
    **  This revision:  2010 September 6
    */
    @Test public void t_af2a() {
        Pointer<Double> a = Pointer.allocateDouble();

        int j = iauAf2a((byte)'-', 45, 13, 27.2, a);

        assertEquals(a, -0.7893115794313644842, 1e-12);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - -
    **   t _ a n p
    **  - - - - - -
    **
    **  Test iauAnp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauAnp, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_anp() {
        assertEquals(iauAnp(-0.1), 6.183185307179586477, 1e-12);
    }

    /*
    **  - - - - - - -
    **   t _ a n p m
    **  - - - - - - -
    **
    **  Test iauAnpm function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauAnpm, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_anpm() {
        assertEquals(iauAnpm(-4.0), 2.283185307179586477, 1e-12);
    }

    /*
    **  - - - - - - -
    **   t _ b i 0 0
    **  - - - - - - -
    **
    **  Test iauBi00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauBi00, assertEquals
    **
    **  This revision:  2009 November 4
    */
    @Test public void t_bi00() {
        Pointer<Double> dpsibi = Pointer.allocateDouble();
        Pointer<Double> depsbi = Pointer.allocateDouble();
        Pointer<Double> dra    = Pointer.allocateDouble();

        iauBi00(dpsibi, depsbi, dra);

        assertEquals(dpsibi, -0.2025309152835086613e-6, 1e-12);
        assertEquals(depsbi, -0.3306041454222147847e-7, 1e-12);
        assertEquals(dra, -0.7078279744199225506e-7, 1e-12);
    }

    /*
    **  - - - - - - -
    **   t _ b p 0 0
    **  - - - - - - -
    **
    **  Test iauBp00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauBp00, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_bp00() {
        Pointer<Pointer<Double>> rb  = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rp  = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rbp = Pointer.allocateDoubles(3, 3);

        iauBp00(2400000.5, 50123.9999, rb.get(), rp.get(), rbp.get());

        assertEquals(rb.get(0).get(0), 0.9999999999999942498, 1e-12);
        assertEquals(rb.get(0).get(1), -0.7078279744199196626e-7, 1e-16);
        assertEquals(rb.get(0).get(2), 0.8056217146976134152e-7, 1e-16);
        assertEquals(rb.get(1).get(0), 0.7078279477857337206e-7, 1e-16);
        assertEquals(rb.get(1).get(1), 0.9999999999999969484, 1e-12);
        assertEquals(rb.get(1).get(2), 0.3306041454222136517e-7, 1e-16);
        assertEquals(rb.get(2).get(0), -0.8056217380986972157e-7, 1e-16);
        assertEquals(rb.get(2).get(1), -0.3306040883980552500e-7, 1e-16);
        assertEquals(rb.get(2).get(2), 0.9999999999999962084, 1e-12);

        assertEquals(rp.get(0).get(0), 0.9999995504864048241, 1e-12);
        assertEquals(rp.get(0).get(1), 0.8696113836207084411e-3, 1e-14);
        assertEquals(rp.get(0).get(2), 0.3778928813389333402e-3, 1e-14);
        assertEquals(rp.get(1).get(0), -0.8696113818227265968e-3, 1e-14);
        assertEquals(rp.get(1).get(1), 0.9999996218879365258, 1e-12);
        assertEquals(rp.get(1).get(2), -0.1690679263009242066e-6, 1e-14);
        assertEquals(rp.get(2).get(0), -0.3778928854764695214e-3, 1e-14);
        assertEquals(rp.get(2).get(1), -0.1595521004195286491e-6, 1e-14);
        assertEquals(rp.get(2).get(2), 0.9999999285984682756, 1e-12);

        assertEquals(rbp.get(0).get(0), 0.9999995505175087260, 1e-12);
        assertEquals(rbp.get(0).get(1), 0.8695405883617884705e-3, 1e-14);
        assertEquals(rbp.get(0).get(2), 0.3779734722239007105e-3, 1e-14);
        assertEquals(rbp.get(1).get(0), -0.8695405990410863719e-3, 1e-14);
        assertEquals(rbp.get(1).get(1), 0.9999996219494925900, 1e-12);
        assertEquals(rbp.get(1).get(2), -0.1360775820404982209e-6, 1e-14);
        assertEquals(rbp.get(2).get(0), -0.3779734476558184991e-3, 1e-14);
        assertEquals(rbp.get(2).get(1), -0.1925857585832024058e-6, 1e-14);
        assertEquals(rbp.get(2).get(2), 0.9999999285680153377, 1e-12);
    }

    /*
    **  - - - - - - -
    **   t _ b p 0 6
    **  - - - - - - -
    **
    **  Test iauBp06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauBp06, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_bp06() {
        Pointer<Pointer<Double>> rb  = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rp  = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rbp = Pointer.allocateDoubles(3, 3);

        iauBp06(2400000.5, 50123.9999, rb.get(), rp.get(), rbp.get());

        assertEquals(rb.get(0).get(0), 0.9999999999999942497, 1e-12);
        assertEquals(rb.get(0).get(1), -0.7078368960971557145e-7, 1e-14);
        assertEquals(rb.get(0).get(2), 0.8056213977613185606e-7, 1e-14);
        assertEquals(rb.get(1).get(0), 0.7078368694637674333e-7, 1e-14);
        assertEquals(rb.get(1).get(1), 0.9999999999999969484, 1e-12);
        assertEquals(rb.get(1).get(2), 0.3305943742989134124e-7, 1e-14);
        assertEquals(rb.get(2).get(0), -0.8056214211620056792e-7, 1e-14);
        assertEquals(rb.get(2).get(1), -0.3305943172740586950e-7, 1e-14);
        assertEquals(rb.get(2).get(2), 0.9999999999999962084, 1e-12);

        assertEquals(rp.get(0).get(0), 0.9999995504864960278, 1e-12);
        assertEquals(rp.get(0).get(1), 0.8696112578855404832e-3, 1e-14);
        assertEquals(rp.get(0).get(2), 0.3778929293341390127e-3, 1e-14);
        assertEquals(rp.get(1).get(0), -0.8696112560510186244e-3, 1e-14);
        assertEquals(rp.get(1).get(1), 0.9999996218880458820, 1e-12);
        assertEquals(rp.get(1).get(2), -0.1691646168941896285e-6, 1e-14);
        assertEquals(rp.get(2).get(0), -0.3778929335557603418e-3, 1e-14);
        assertEquals(rp.get(2).get(1), -0.1594554040786495076e-6, 1e-14);
        assertEquals(rp.get(2).get(2), 0.9999999285984501222, 1e-12);

        assertEquals(rbp.get(0).get(0), 0.9999995505176007047, 1e-12);
        assertEquals(rbp.get(0).get(1), 0.8695404617348208406e-3, 1e-14);
        assertEquals(rbp.get(0).get(2), 0.3779735201865589104e-3, 1e-14);
        assertEquals(rbp.get(1).get(0), -0.8695404723772031414e-3, 1e-14);
        assertEquals(rbp.get(1).get(1), 0.9999996219496027161, 1e-12);
        assertEquals(rbp.get(1).get(2), -0.1361752497080270143e-6, 1e-14);
        assertEquals(rbp.get(2).get(0), -0.3779734957034089490e-3, 1e-14);
        assertEquals(rbp.get(2).get(1), -0.1924880847894457113e-6, 1e-14);
        assertEquals(rbp.get(2).get(2), 0.9999999285679971958, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ b p n 2 x y
    **  - - - - - - - - -
    **
    **  Test iauBpn2xy function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauBpn2xy, assertEquals
    **
    **  This revision:  2008 May 26
    */
    @Test public void t_bpn2xy() {
        Pointer<Pointer<Double>> rbpn  = Pointer.allocateDoubles(3, 3);
        Pointer<Double> x = Pointer.allocateDouble();
        Pointer<Double> y = Pointer.allocateDouble();

        rbpn.get(0).set(0, 9.999962358680738e-1);
        rbpn.get(0).set(1, -2.516417057665452e-3);
        rbpn.get(0).set(2, -1.093569785342370e-3);

        rbpn.get(1).set(0,  2.516462370370876e-3);
        rbpn.get(1).set(1,  9.999968329010883e-1);
        rbpn.get(1).set(2,  4.006159587358310e-5);

        rbpn.get(2).set(0,  1.093465510215479e-3);
        rbpn.get(2).set(1, -4.281337229063151e-5);
        rbpn.get(2).set(2,  9.999994012499173e-1);

        iauBpn2xy(rbpn.get(), x, y);

        assertEquals(x,  1.093465510215479e-3, 1e-12);
        assertEquals(y, -4.281337229063151e-5, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ c 2 i 0 0 a
    **  - - - - - - - - -
    **
    **  Test iauC2i00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2i00a, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_c2i00a() {
        Pointer<Pointer<Double>> rc2i = Pointer.allocateDoubles(3, 3);

        iauC2i00a(2400000.5, 53736.0, rc2i.get());

        assertEquals(rc2i.get(0).get(0), 0.9999998323037165557, 1e-12);
        assertEquals(rc2i.get(0).get(1), 0.5581526348992140183e-9, 1e-12);
        assertEquals(rc2i.get(0).get(2), -0.5791308477073443415e-3, 1e-12);

        assertEquals(rc2i.get(1).get(0), -0.2384266227870752452e-7, 1e-12);
        assertEquals(rc2i.get(1).get(1), 0.9999999991917405258, 1e-12);
        assertEquals(rc2i.get(1).get(2), -0.4020594955028209745e-4, 1e-12);

        assertEquals(rc2i.get(2).get(0), 0.5791308472168152904e-3, 1e-12);
        assertEquals(rc2i.get(2).get(1), 0.4020595661591500259e-4, 1e-12);
        assertEquals(rc2i.get(2).get(2), 0.9999998314954572304, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ c 2 i 0 0 b
    **  - - - - - - - - -
    **
    **  Test iauC2i00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2i00b, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_c2i00b() {
        Pointer<Pointer<Double>> rc2i = Pointer.allocateDoubles(3, 3);

        iauC2i00b(2400000.5, 53736.0, rc2i.get());

        assertEquals(rc2i.get(0).get(0), 0.9999998323040954356, 1e-12);
        assertEquals(rc2i.get(0).get(1), 0.5581526349131823372e-9, 1e-12);
        assertEquals(rc2i.get(0).get(2), -0.5791301934855394005e-3, 1e-12);

        assertEquals(rc2i.get(1).get(0), -0.2384239285499175543e-7, 1e-12);
        assertEquals(rc2i.get(1).get(1), 0.9999999991917574043, 1e-12);
        assertEquals(rc2i.get(1).get(2), -0.4020552974819030066e-4, 1e-12);

        assertEquals(rc2i.get(2).get(0), 0.5791301929950208873e-3, 1e-12);
        assertEquals(rc2i.get(2).get(1), 0.4020553681373720832e-4, 1e-12);
        assertEquals(rc2i.get(2).get(2), 0.9999998314958529887, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ c 2 i 0 6 a
    **  - - - - - - - - -
    **
    **  Test iauC2i06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2i06a, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_c2i06a() {
        Pointer<Pointer<Double>> rc2i = Pointer.allocateDoubles(3, 3);

        iauC2i06a(2400000.5, 53736.0, rc2i.get());

        assertEquals(rc2i.get(0).get(0), 0.9999998323037159379, 1e-12);
        assertEquals(rc2i.get(0).get(1), 0.5581121329587613787e-9, 1e-12);
        assertEquals(rc2i.get(0).get(2), -0.5791308487740529749e-3, 1e-12);

        assertEquals(rc2i.get(1).get(0), -0.2384253169452306581e-7, 1e-12);
        assertEquals(rc2i.get(1).get(1), 0.9999999991917467827, 1e-12);
        assertEquals(rc2i.get(1).get(2), -0.4020579392895682558e-4, 1e-12);

        assertEquals(rc2i.get(2).get(0), 0.5791308482835292617e-3, 1e-12);
        assertEquals(rc2i.get(2).get(1), 0.4020580099454020310e-4, 1e-12);
        assertEquals(rc2i.get(2).get(2), 0.9999998314954628695, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ c 2 i b p n
    **  - - - - - - - - -
    **
    **  Test iauC2ibpn function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2ibpn, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_c2ibpn() {
        Pointer<Pointer<Double>> rbpn = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rc2i = Pointer.allocateDoubles(3, 3);

        rbpn.get(0).set(0,  9.999962358680738e-1);
        rbpn.get(0).set(1, -2.516417057665452e-3);
        rbpn.get(0).set(2, -1.093569785342370e-3);

        rbpn.get(1).set(0,  2.516462370370876e-3);
        rbpn.get(1).set(1,  9.999968329010883e-1);
        rbpn.get(1).set(2,  4.006159587358310e-5);

        rbpn.get(2).set(0,  1.093465510215479e-3);
        rbpn.get(2).set(1, -4.281337229063151e-5);
        rbpn.get(2).set(2,  9.999994012499173e-1);

        iauC2ibpn(2400000.5, 50123.9999, rbpn.get(), rc2i.get());

        assertEquals(rc2i.get(0).get(0), 0.9999994021664089977, 1e-12);
        assertEquals(rc2i.get(0).get(1), -0.3869195948017503664e-8, 1e-12);
        assertEquals(rc2i.get(0).get(2), -0.1093465511383285076e-2, 1e-12);

        assertEquals(rc2i.get(1).get(0), 0.5068413965715446111e-7, 1e-12);
        assertEquals(rc2i.get(1).get(1), 0.9999999990835075686, 1e-12);
        assertEquals(rc2i.get(1).get(2), 0.4281334246452708915e-4, 1e-12);

        assertEquals(rc2i.get(2).get(0), 0.1093465510215479000e-2, 1e-12);
        assertEquals(rc2i.get(2).get(1), -0.4281337229063151000e-4, 1e-12);
        assertEquals(rc2i.get(2).get(2), 0.9999994012499173103, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ c 2 i x y
    **  - - - - - - - -
    **
    **  Test iauC2ixy function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2ixy, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_c2ixy() {
        Pointer<Pointer<Double>> rc2i  = Pointer.allocateDoubles(3, 3);

        double x = 0.5791308486706011000e-3;
        double y = 0.4020579816732961219e-4;

        iauC2ixy(2400000.5, 53736, x, y, rc2i.get());

        assertEquals(rc2i.get(0).get(0), 0.9999998323037157138, 1e-12);
        assertEquals(rc2i.get(0).get(1), 0.5581526349032241205e-9, 1e-12);
        assertEquals(rc2i.get(0).get(2), -0.5791308491611263745e-3, 1e-12);

        assertEquals(rc2i.get(1).get(0), -0.2384257057469842953e-7, 1e-12);
        assertEquals(rc2i.get(1).get(1), 0.9999999991917468964, 1e-12);
        assertEquals(rc2i.get(1).get(2), -0.4020579110172324363e-4, 1e-12);

        assertEquals(rc2i.get(2).get(0), 0.5791308486706011000e-3, 1e-12);
        assertEquals(rc2i.get(2).get(1), 0.4020579816732961219e-4, 1e-12);
        assertEquals(rc2i.get(2).get(2), 0.9999998314954627590, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ c 2 i x y s
    **  - - - - - - - - -
    **
    **  Test iauC2ixys function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2ixys, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_c2ixys() {
        Pointer<Pointer<Double>> rc2i  = Pointer.allocateDoubles(3, 3);

        double x =  0.5791308486706011000e-3;
        double y =  0.4020579816732961219e-4;
        double s = -0.1220040848472271978e-7;

        iauC2ixys(x, y, s, rc2i.get());

        assertEquals(rc2i.get(0).get(0), 0.9999998323037157138, 1e-12);
        assertEquals(rc2i.get(0).get(1), 0.5581984869168499149e-9, 1e-12);
        assertEquals(rc2i.get(0).get(2), -0.5791308491611282180e-3, 1e-12);

        assertEquals(rc2i.get(1).get(0), -0.2384261642670440317e-7, 1e-12);
        assertEquals(rc2i.get(1).get(1), 0.9999999991917468964, 1e-12);
        assertEquals(rc2i.get(1).get(2), -0.4020579110169668931e-4, 1e-12);

        assertEquals(rc2i.get(2).get(0), 0.5791308486706011000e-3, 1e-12);
        assertEquals(rc2i.get(2).get(1), 0.4020579816732961219e-4, 1e-12);
        assertEquals(rc2i.get(2).get(2), 0.9999998314954627590, 1e-12);
    }

    /*
    **  - - - - - -
    **   t _ c 2 s
    **  - - - - - -
    **
    **  Test iauC2s function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2s, assertEquals
    **
    **  This revision:  2008 May 27
    */
    @Test public void t_c2s() {
        Pointer<Double> p     = Pointer.allocateDoubles(3);
        Pointer<Double> theta = Pointer.allocateDouble();
        Pointer<Double> phi   = Pointer.allocateDouble();

        p.set(0, 100.0);
        p.set(1, -50.0);
        p.set(2,  25.0);

        iauC2s(p, theta, phi);

        assertEquals(theta, -0.4636476090008061162, 1e-14);
        assertEquals(phi, 0.2199879773954594463, 1e-14);
    }

    /*
    **  - - - - - - - - -
    **   t _ c 2 t 0 0 a
    **  - - - - - - - - -
    **
    **  Test iauC2t00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2t00a, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_c2t00a() {
        Pointer<Pointer<Double>> rc2t  = Pointer.allocateDoubles(3, 3);

        double tta = 2400000.5;
        double uta = 2400000.5;
        double ttb = 53736.0;
        double utb = 53736.0;
        double xp = 2.55060238e-7;
        double yp = 1.860359247e-6;

        iauC2t00a(tta, ttb, uta, utb, xp, yp, rc2t.get());

        assertEquals(rc2t.get(0).get(0), -0.1810332128307182668, 1e-12);
        assertEquals(rc2t.get(0).get(1), 0.9834769806938457836, 1e-12);
        assertEquals(rc2t.get(0).get(2), 0.6555535638688341725e-4, 1e-12);

        assertEquals(rc2t.get(1).get(0), -0.9834768134135984552, 1e-12);
        assertEquals(rc2t.get(1).get(1), -0.1810332203649520727, 1e-12);
        assertEquals(rc2t.get(1).get(2), 0.5749801116141056317e-3, 1e-12);

        assertEquals(rc2t.get(2).get(0), 0.5773474014081406921e-3, 1e-12);
        assertEquals(rc2t.get(2).get(1), 0.3961832391770163647e-4, 1e-12);
        assertEquals(rc2t.get(2).get(2), 0.9999998325501692289, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ c 2 t 0 0 b
    **  - - - - - - - - -
    **
    **  Test iauC2t00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2t00b, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_c2t00b() {
        Pointer<Pointer<Double>> rc2t  = Pointer.allocateDoubles(3, 3);

        double tta = 2400000.5;
        double uta = 2400000.5;
        double ttb = 53736.0;
        double utb = 53736.0;
        double xp = 2.55060238e-7;
        double yp = 1.860359247e-6;

        iauC2t00b(tta, ttb, uta, utb, xp, yp, rc2t.get());

        assertEquals(rc2t.get(0).get(0), -0.1810332128439678965, 1e-12);
        assertEquals(rc2t.get(0).get(1), 0.9834769806913872359, 1e-12);
        assertEquals(rc2t.get(0).get(2), 0.6555565082458415611e-4, 1e-12);

        assertEquals(rc2t.get(1).get(0), -0.9834768134115435923, 1e-12);
        assertEquals(rc2t.get(1).get(1), -0.1810332203784001946, 1e-12);
        assertEquals(rc2t.get(1).get(2), 0.5749793922030017230e-3, 1e-12);

        assertEquals(rc2t.get(2).get(0), 0.5773467471863534901e-3, 1e-12);
        assertEquals(rc2t.get(2).get(1), 0.3961790411549945020e-4, 1e-12);
        assertEquals(rc2t.get(2).get(2), 0.9999998325505635738, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ c 2 t 0 6 a
    **  - - - - - - - - -
    **
    **  Test iauC2t06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2t06a, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_c2t06a() {
        Pointer<Pointer<Double>> rc2t  = Pointer.allocateDoubles(3, 3);

        double tta = 2400000.5;
        double uta = 2400000.5;
        double ttb = 53736.0;
        double utb = 53736.0;
        double xp = 2.55060238e-7;
        double yp = 1.860359247e-6;

        iauC2t06a(tta, ttb, uta, utb, xp, yp, rc2t.get());

        assertEquals(rc2t.get(0).get(0), -0.1810332128305897282, 1e-12);
        assertEquals(rc2t.get(0).get(1), 0.9834769806938592296, 1e-12);
        assertEquals(rc2t.get(0).get(2), 0.6555550962998436505e-4, 1e-12);

        assertEquals(rc2t.get(1).get(0), -0.9834768134136214897, 1e-12);
        assertEquals(rc2t.get(1).get(1), -0.1810332203649130832, 1e-12);
        assertEquals(rc2t.get(1).get(2), 0.5749800844905594110e-3, 1e-12);

        assertEquals(rc2t.get(2).get(0), 0.5773474024748545878e-3, 1e-12);
        assertEquals(rc2t.get(2).get(1), 0.3961816829632690581e-4, 1e-12);
        assertEquals(rc2t.get(2).get(2), 0.9999998325501747785, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ c 2 t c i o
    **  - - - - - - - - -
    **
    **  Test iauC2tcio function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2tcio, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_c2tcio() {
        Pointer<Pointer<Double>> rc2i  = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rpom  = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rc2t  = Pointer.allocateDoubles(3, 3);

        rc2i.get(0).set(0,  0.9999998323037164738);
        rc2i.get(0).set(1,  0.5581526271714303683e-9);
        rc2i.get(0).set(2, -0.5791308477073443903e-3);

        rc2i.get(1).set(0, -0.2384266227524722273e-7);
        rc2i.get(1).set(1,  0.9999999991917404296);
        rc2i.get(1).set(2, -0.4020594955030704125e-4);

        rc2i.get(2).set(0,  0.5791308472168153320e-3);
        rc2i.get(2).set(1,  0.4020595661593994396e-4);
        rc2i.get(2).set(2,  0.9999998314954572365);

        double era = 1.75283325530307;

        rpom.get(0).set(0,  0.9999999999999674705);
        rpom.get(0).set(1, -0.1367174580728847031e-10);
        rpom.get(0).set(2,  0.2550602379999972723e-6);

        rpom.get(1).set(0,  0.1414624947957029721e-10);
        rpom.get(1).set(1,  0.9999999999982694954);
        rpom.get(1).set(2, -0.1860359246998866338e-5);

        rpom.get(2).set(0, -0.2550602379741215275e-6);
        rpom.get(2).set(1,  0.1860359247002413923e-5);
        rpom.get(2).set(2,  0.9999999999982369658);


        iauC2tcio(rc2i.get(), era, rpom.get(), rc2t.get());

        assertEquals(rc2t.get(0).get(0), -0.1810332128307110439, 1e-12);
        assertEquals(rc2t.get(0).get(1), 0.9834769806938470149, 1e-12);
        assertEquals(rc2t.get(0).get(2), 0.6555535638685466874e-4, 1e-12);

        assertEquals(rc2t.get(1).get(0), -0.9834768134135996657, 1e-12);
        assertEquals(rc2t.get(1).get(1), -0.1810332203649448367, 1e-12);
        assertEquals(rc2t.get(1).get(2), 0.5749801116141106528e-3, 1e-12);

        assertEquals(rc2t.get(2).get(0), 0.5773474014081407076e-3, 1e-12);
        assertEquals(rc2t.get(2).get(1), 0.3961832391772658944e-4, 1e-12);
        assertEquals(rc2t.get(2).get(2), 0.9999998325501691969, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ c 2 t e q x
    **  - - - - - - - - -
    **
    **  Test iauC2teqx function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2teqx, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_c2teqx() {
        Pointer<Pointer<Double>> rbpn  = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rpom  = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rc2t  = Pointer.allocateDoubles(3, 3);

        rbpn.get(0).set(0,  0.9999989440476103608);
        rbpn.get(0).set(1, -0.1332881761240011518e-2);
        rbpn.get(0).set(2, -0.5790767434730085097e-3);

        rbpn.get(1).set(0,  0.1332858254308954453e-2);
        rbpn.get(1).set(1,  0.9999991109044505944);
        rbpn.get(1).set(2, -0.4097782710401555759e-4);

        rbpn.get(2).set(0,  0.5791308472168153320e-3);
        rbpn.get(2).set(1,  0.4020595661593994396e-4);
        rbpn.get(2).set(2,  0.9999998314954572365);

        double gst = 1.754166138040730516;

        rpom.get(0).set(0,  0.9999999999999674705);
        rpom.get(0).set(1, -0.1367174580728847031e-10);
        rpom.get(0).set(2,  0.2550602379999972723e-6);

        rpom.get(1).set(0,  0.1414624947957029721e-10);
        rpom.get(1).set(1,  0.9999999999982694954);
        rpom.get(1).set(2, -0.1860359246998866338e-5);

        rpom.get(2).set(0, -0.2550602379741215275e-6);
        rpom.get(2).set(1,  0.1860359247002413923e-5);
        rpom.get(2).set(2,  0.9999999999982369658);

        iauC2teqx(rbpn.get(), gst, rpom.get(), rc2t.get());

        assertEquals(rc2t.get(0).get(0), -0.1810332128528685730, 1e-12);
        assertEquals(rc2t.get(0).get(1), 0.9834769806897685071, 1e-12);
        assertEquals(rc2t.get(0).get(2), 0.6555535639982634449e-4, 1e-12);

        assertEquals(rc2t.get(1).get(0), -0.9834768134095211257, 1e-12);
        assertEquals(rc2t.get(1).get(1), -0.1810332203871023800, 1e-12);
        assertEquals(rc2t.get(1).get(2), 0.5749801116126438962e-3, 1e-12);

        assertEquals(rc2t.get(2).get(0), 0.5773474014081539467e-3, 1e-12);
        assertEquals(rc2t.get(2).get(1), 0.3961832391768640871e-4, 1e-12);
        assertEquals(rc2t.get(2).get(2), 0.9999998325501691969, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ c 2 t p e
    **  - - - - - - - -
    **
    **  Test iauC2tpe function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2tpe, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_c2tpe() {
        Pointer<Pointer<Double>> rc2t  = Pointer.allocateDoubles(3, 3);

        double tta = 2400000.5;
        double uta = 2400000.5;
        double ttb = 53736.0;
        double utb = 53736.0;
        double deps =  0.4090789763356509900;
        double dpsi = -0.9630909107115582393e-5;
        double xp = 2.55060238e-7;
        double yp = 1.860359247e-6;

        iauC2tpe(tta, ttb, uta, utb, dpsi, deps, xp, yp, rc2t.get());

        assertEquals(rc2t.get(0).get(0), -0.1813677995763029394, 1e-12);
        assertEquals(rc2t.get(0).get(1), 0.9023482206891683275, 1e-12);
        assertEquals(rc2t.get(0).get(2), -0.3909902938641085751, 1e-12);

        assertEquals(rc2t.get(1).get(0), -0.9834147641476804807, 1e-12);
        assertEquals(rc2t.get(1).get(1), -0.1659883635434995121, 1e-12);
        assertEquals(rc2t.get(1).get(2), 0.7309763898042819705e-1, 1e-12);

        assertEquals(rc2t.get(2).get(0), 0.1059685430673215247e-2, 1e-12);
        assertEquals(rc2t.get(2).get(1), 0.3977631855605078674, 1e-12);
        assertEquals(rc2t.get(2).get(2), 0.9174875068792735362, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ c 2 t x y
    **  - - - - - - - -
    **
    **  Test iauC2txy function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauC2txy, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_c2txy() {
        Pointer<Pointer<Double>> rc2t  = Pointer.allocateDoubles(3, 3);

        double tta = 2400000.5;
        double uta = 2400000.5;
        double ttb = 53736.0;
        double utb = 53736.0;
        double x = 0.5791308486706011000e-3;
        double y = 0.4020579816732961219e-4;
        double xp = 2.55060238e-7;
        double yp = 1.860359247e-6;

        iauC2txy(tta, ttb, uta, utb, x, y, xp, yp, rc2t.get());

        assertEquals(rc2t.get(0).get(0), -0.1810332128306279253, 1e-12);
        assertEquals(rc2t.get(0).get(1), 0.9834769806938520084, 1e-12);
        assertEquals(rc2t.get(0).get(2), 0.6555551248057665829e-4, 1e-12);

        assertEquals(rc2t.get(1).get(0), -0.9834768134136142314, 1e-12);
        assertEquals(rc2t.get(1).get(1), -0.1810332203649529312, 1e-12);
        assertEquals(rc2t.get(1).get(2), 0.5749800843594139912e-3, 1e-12);

        assertEquals(rc2t.get(2).get(0), 0.5773474028619264494e-3, 1e-12);
        assertEquals(rc2t.get(2).get(1), 0.3961816546911624260e-4, 1e-12);
        assertEquals(rc2t.get(2).get(2), 0.9999998325501746670, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ c a l 2 j d
    **  - - - - - - - - -
    **
    **  Test iauCal2jd function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauCal2jd, assertEquals, assertEquals
    **
    **  This revision:  2008 May 27
    */
    @Test public void t_cal2jd() {
        Pointer<Double> djm0 = Pointer.allocateDouble();
        Pointer<Double> djm  = Pointer.allocateDouble();

        int j = iauCal2jd(2003, 6, 1, djm0, djm);

        assertEquals(djm0, 2400000.5, 0.0);
        assertEquals(djm,    52791.0, 0.0);

        assertEquals(j, 0);
    }

    /*
    **  - - - - -
    **   t _ c p
    **  - - - - -
    **
    **  Test iauCp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauCp, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_cp() {
        Pointer<Double> p = Pointer.allocateDoubles(3);
        Pointer<Double> c = Pointer.allocateDoubles(3);

        p.set(0,  0.3);
        p.set(1,  1.2);
        p.set(2, -2.5);

        iauCp(p, c);

        assertEquals(c.get(0),  0.3, 0.0);
        assertEquals(c.get(1),  1.2, 0.0);
        assertEquals(c.get(2), -2.5, 0.0);
    }

    /*
    **  - - - - - -
    **   t _ c p v
    **  - - - - - -
    **
    **  Test iauCpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauCpv, assertEquals
    **
    **  This revision:  2008 May 25
    */
    @Test public void t_cpv() {
        Pointer<Pointer<Double>> pv = Pointer.allocateDoubles(2, 3);
        Pointer<Pointer<Double>> c  = Pointer.allocateDoubles(2, 3);

        pv.get(0).set(0,  0.3);
        pv.get(0).set(1,  1.2);
        pv.get(0).set(2, -2.5);

        pv.get(1).set(0, -0.5);
        pv.get(1).set(1,  3.1);
        pv.get(1).set(2,  0.9);

        iauCpv(pv.get(), c.get());

        assertEquals(c.get(0).get(0),  0.3, 0.0);
        assertEquals(c.get(0).get(1),  1.2, 0.0);
        assertEquals(c.get(0).get(2), -2.5, 0.0);

        assertEquals(c.get(1).get(0), -0.5, 0.0);
        assertEquals(c.get(1).get(1),  3.1, 0.0);
        assertEquals(c.get(1).get(2),  0.9, 0.0);
    }

    /*
    **  - - - - -
    **   t _ c r
    **  - - - - -
    **
    **  Test iauCr function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauCr, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_cr() {
        Pointer<Pointer<Double>> r = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> c = Pointer.allocateDoubles(3, 3);

        r.get(0).set(0, 2.0);
        r.get(0).set(1, 3.0);
        r.get(0).set(2, 2.0);

        r.get(1).set(0, 3.0);
        r.get(1).set(1, 2.0);
        r.get(1).set(2, 3.0);

        r.get(2).set(0, 3.0);
        r.get(2).set(1, 4.0);
        r.get(2).set(2, 5.0);

        iauCr(r.get(), c.get());

        assertEquals(c.get(0).get(0), 2.0, 0.0);
        assertEquals(c.get(0).get(1), 3.0, 0.0);
        assertEquals(c.get(0).get(2), 2.0, 0.0);

        assertEquals(c.get(1).get(0), 3.0, 0.0);
        assertEquals(c.get(1).get(1), 2.0, 0.0);
        assertEquals(c.get(1).get(2), 3.0, 0.0);

        assertEquals(c.get(2).get(0), 3.0, 0.0);
        assertEquals(c.get(2).get(1), 4.0, 0.0);
        assertEquals(c.get(2).get(2), 5.0, 0.0);
    }

    /*
    **  - - - - - - - -
    **   t _ d 2 d t f
    **  - - - - - - - -
    **
    **  Test iauD2dtf function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauD2dtf, assertEquals
    **
    **  This revision:  2010 September 7
    */
    @Test public void t_d2dtf( ) {
        Pointer<Integer> ihmsf = Pointer.allocateInts(4);
        Pointer<Integer> iy = Pointer.allocateInt();
        Pointer<Integer> im = Pointer.allocateInt();
        Pointer<Integer> id = Pointer.allocateInt();

        int j = iauD2dtf( Pointer.pointerToCString("UTC"), 5, 2400000.5, 49533.99999, iy, im, id, ihmsf);

        assertEquals(iy, 1994);
        assertEquals(im, 6);
        assertEquals(id, 30);
        assertEquals(ihmsf.get(0), 23);
        assertEquals(ihmsf.get(1), 59);
        assertEquals(ihmsf.get(2), 60);
        assertEquals(ihmsf.get(3), 13599);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - -
    **   t _ d 2 t f
    **  - - - - - - -
    **
    **  Test iauD2tf function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauD2tf, assertEquals, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_d2tf() {
        Pointer<Integer> ihmsf = Pointer.allocateInts(4);
        Pointer<Byte> s = Pointer.allocateByte();

        iauD2tf(4, -0.987654321, s, ihmsf);

        assertEquals(s, '-');

        assertEquals(ihmsf.get(0), 23);
        assertEquals(ihmsf.get(1), 42);
        assertEquals(ihmsf.get(2), 13);
        assertEquals(ihmsf.get(3), 3333);
    }

    /*
    **  - - - - - -
    **   t _ d a t
    **  - - - - - -
    **
    **  Test iauDat function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauDat, assertEquals, assertEquals
    **
    **  This revision:  2008 November 29
    */
    @Test public void t_dat() {
        Pointer<Double> deltat = Pointer.allocateDouble();

        int j = iauDat(2003, 6, 1, 0.0, deltat);

        assertEquals(deltat, 32.0, 0.0);
        assertEquals(j, 0);

        j = iauDat(2008, 1, 17, 0.0, deltat);

        assertEquals(deltat, 33.0, 0.0);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - -
    **   t _ d t d b
    **  - - - - - - -
    **
    **  Test iauDtdb function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauDtdb, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_dtdb() {
        double dtdb = iauDtdb(2448939.5, 0.123, 0.76543, 5.0123, 5525.242, 3190.0);
        assertEquals(dtdb, -0.1280368005936998991e-2, 1e-15);
    }

    /*
    **  - - - - - - - -
    **   t _ d t f 2 d
    **  - - - - - - - -
    **
    **  Test iauDtf2d function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauDtf2d, assertEquals, assertEquals
    **
    **  This revision:  2010 September 7
    */
    @Test public void t_dtf2d() {
        Pointer<Double> u1 = Pointer.allocateDouble();
        Pointer<Double> u2 = Pointer.allocateDouble();

        int j = iauDtf2d( Pointer.pointerToCString("UTC"), 1994, 6, 30, 23, 59, 60.13599, u1, u2);

        assertEquals(u1.get()+u2.get(), 2449534.49999, 1e-6);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - -
    **   t _ e e 0 0
    **  - - - - - - -
    **
    **  Test iauEe00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEe00, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_ee00() {
        double epsa =  0.4090789763356509900;
        double dpsi = -0.9630909107115582393e-5;

        double ee = iauEe00(2400000.5, 53736.0, epsa, dpsi);

        assertEquals(ee, -0.8834193235367965479e-5, 1e-18);
    }

    /*
    **  - - - - - - - -
    **   t _ e e 0 0 a
    **  - - - - - - - -
    **
    **  Test iauEe00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEe00a, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_ee00a() {
        double ee = iauEe00a(2400000.5, 53736.0);
        assertEquals(ee, -0.8834192459222588227e-5, 1e-18);
    }

    /*
    **  - - - - - - - -
    **   t _ e e 0 0 b
    **  - - - - - - - -
    **
    **  Test iauEe00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEe00b, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_ee00b() {
        double ee = iauEe00b(2400000.5, 53736.0);
        assertEquals(ee, -0.8835700060003032831e-5, 1e-18);
    }

    /*
    **  - - - - - - - -
    **   t _ e e 0 6 a
    **  - - - - - - - -
    **
    **  Test iauEe06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEe06a, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_ee06a() {
        double ee = iauEe06a(2400000.5, 53736.0);
        assertEquals(ee, -0.8834195072043790156e-5, 1e-15);
    }

    /*
    **  - - - - - - - - -
    **   t _ e e c t 0 0
    **  - - - - - - - - -
    **
    **  Test iauEect00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEect00, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_eect00() {
        double eect = iauEect00(2400000.5, 53736.0);
        assertEquals(eect, 0.2046085004885125264e-8, 1e-20);
    }

    /*
    **  - - - - - - - -
    **   t _ e f o r m
    **  - - - - - - - -
    **
    **  Test iauEform function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEform, assertEquals, assertEquals
    **
    **  This revision:  2012 February 23
    */
    @Test public void t_eform() {
        Pointer<Double> a = Pointer.allocateDouble();
        Pointer<Double> f = Pointer.allocateDouble();

        int j = iauEform(0, a, f);
        assertEquals(j, -1);

        j = iauEform(WGS84, a, f);
        assertEquals(j, 0);
        assertEquals(a, 6378137.0, 1e-10);
        assertEquals(f, 0.0033528106647474807, 1e-18);

        j = iauEform(GRS80, a, f);
        assertEquals(j, 0);
        assertEquals(a, 6378137.0, 1e-10);
        assertEquals(f, 0.0033528106811823189, 1e-18);

        j = iauEform(WGS72, a, f);
        assertEquals(j, 0);
        assertEquals(a, 6378135.0, 1e-10);
        assertEquals(f, 0.0033527794541675049, 1e-18);

        j = iauEform(4, a, f);
        assertEquals(j, -1);
    }

    /*
    **  - - - - - - - -
    **   t _ e o 0 6 a
    **  - - - - - - - -
    **
    **  Test iauEo06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEo06a, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_eo06a() {
        double eo = iauEo06a(2400000.5, 53736.0);
        assertEquals(eo, -0.1332882371941833644e-2, 1e-15);
    }

    /*
    **  - - - - - - -
    **   t _ e o r s
    **  - - - - - - -
    **
    **  Test iauEors function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEors, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_eors() {
        Pointer<Pointer<Double>> rnpb = Pointer.allocateDoubles(3, 3);

        rnpb.get(0).set(0,  0.9999989440476103608);
        rnpb.get(0).set(1, -0.1332881761240011518e-2);
        rnpb.get(0).set(2, -0.5790767434730085097e-3);

        rnpb.get(1).set(0,  0.1332858254308954453e-2);
        rnpb.get(1).set(1,  0.9999991109044505944);
        rnpb.get(1).set(2, -0.4097782710401555759e-4);

        rnpb.get(2).set(0,  0.5791308472168153320e-3);
        rnpb.get(2).set(1,  0.4020595661593994396e-4);
        rnpb.get(2).set(2,  0.9999998314954572365);

        double s = -0.1220040848472271978e-7;

        double eo = iauEors(rnpb.get(), s);

        assertEquals(eo, -0.1332882715130744606e-2, 1e-14);
    }

    /*
    **  - - - - - -
    **   t _ e p b
    **  - - - - - -
    **
    **  Test iauEpb function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEpb, assertEquals
    **
    **  This revision:  2008 May 27
    */
    @Test public void t_epb() {
        double epb = iauEpb(2415019.8135, 30103.18648);
        assertEquals(epb, 1982.418424159278580, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ e p b 2 j d
    **  - - - - - - - - -
    **
    **  Test iauEpb2jd function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEpb2jd, assertEquals
    **
    **  This revision:  2008 November 29
    */
    @Test public void t_epb2jd() {
        Pointer<Double> djm0 = Pointer.allocateDouble();
        Pointer<Double> djm  = Pointer.allocateDouble();

        double epb = 1957.3;

        iauEpb2jd(epb, djm0, djm);

        assertEquals(djm0, 2400000.5, 1e-9);
        assertEquals(djm, 35948.1915101513, 1e-9);
    }

    /*
    **  - - - - - -
    **   t _ e p j
    **  - - - - - -
    **
    **  Test iauEpj function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEpj, assertEquals
    **
    **  This revision:  2008 May 27
    */
    @Test public void t_epj()
    {
        double epj = iauEpj(2451545, -7392.5);
        assertEquals(epj, 1979.760438056125941, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ e p j 2 j d
    **  - - - - - - - - -
    **
    **  Test iauEpj2jd function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEpj2jd, assertEquals
    **
    **  This revision:  2008 November 29
    */
    @Test public void t_epj2jd() {
        Pointer<Double> djm0 = Pointer.allocateDouble();
        Pointer<Double> djm  = Pointer.allocateDouble();

        double epj = 1996.8;

        iauEpj2jd(epj, djm0, djm);

        assertEquals(djm0, 2400000.5, 1e-9);
        assertEquals(djm,    50375.7, 1e-9);
    }

    /*
    **  - - - - - - - -
    **   t _ e p v 0 0
    **  - - - - - - - -
    **
    **  Test iauEpv00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called: iauEpv00, assertEquals, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_epv00() {
        Pointer<Pointer<Double>> pvh = Pointer.allocateDoubles(2, 3);
        Pointer<Pointer<Double>> pvb = Pointer.allocateDoubles(2, 3);

        int j = iauEpv00(2400000.5, 53411.52501161, pvh.get(), pvb.get());

        assertEquals(pvh.get(0).get(0), -0.7757238809297706813, 1e-14);
        assertEquals(pvh.get(0).get(1), 0.5598052241363340596, 1e-14);
        assertEquals(pvh.get(0).get(2), 0.2426998466481686993, 1e-14);

        assertEquals(pvh.get(1).get(0), -0.1091891824147313846e-1, 1e-15);
        assertEquals(pvh.get(1).get(1), -0.1247187268440845008e-1, 1e-15);
        assertEquals(pvh.get(1).get(2), -0.5407569418065039061e-2, 1e-15);

        assertEquals(pvb.get(0).get(0), -0.7714104440491111971, 1e-14);
        assertEquals(pvb.get(0).get(1), 0.5598412061824171323, 1e-14);
        assertEquals(pvb.get(0).get(2), 0.2425996277722452400, 1e-14);

        assertEquals(pvb.get(1).get(0), -0.1091874268116823295e-1, 1e-15);
        assertEquals(pvb.get(1).get(1), -0.1246525461732861538e-1, 1e-15);
        assertEquals(pvb.get(1).get(2), -0.5404773180966231279e-2, 1e-15);

        assertEquals(j, 0);
    }

    /*
    **  - - - - - - - - -
    **   t _ e q e q 9 4
    **  - - - - - - - - -
    **
    **  Test iauEqeq94 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEqeq94, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_eqeq94() {
        double eqeq = iauEqeq94(2400000.5, 41234.0);
        assertEquals(eqeq, 0.5357758254609256894e-4, 1e-17);
    }

    /*
    **  - - - - - - - -
    **   t _ e r a 0 0
    **  - - - - - - - -
    **
    **  Test iauEra00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauEra00, assertEquals
    **
    **  This revision:  2008 May 26
    */
    @Test public void t_era00() {
        double era00 = iauEra00(2400000.5, 54388.0);
        assertEquals(era00, 0.4022837240028158102, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ f a d 0 3
    **  - - - - - - - -
    **
    **  Test iauFad03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFad03, assertEquals
    **
    **  This revision:  2008 May 22
    */
    @Test public void t_fad03() {
        assertEquals(iauFad03(0.80), 1.946709205396925672, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ f a e 0 3
    **  - - - - - - - -
    **
    **  Test iauFae03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFae03, assertEquals
    **
    **  This revision:  2008 May 22
    */
    @Test public void t_fae03() {
        assertEquals(iauFae03(0.80), 1.744713738913081846, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ f a f 0 3
    **  - - - - - - - -
    **
    **  Test iauFaf03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFaf03, assertEquals
    **
    **  This revision:  2008 May 22
    */
    @Test public void t_faf03() {
        assertEquals(iauFaf03(0.80), 0.2597711366745499518, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ f a j u 0 3
    **  - - - - - - - - -
    **
    **  Test iauFaju03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFaju03, assertEquals
    **
    **  This revision:  2008 May 22
    */
    @Test public void t_faju03() {
        assertEquals(iauFaju03(0.80), 5.275711665202481138, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ f a l 0 3
    **  - - - - - - - -
    **
    **  Test iauFal03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFal03, assertEquals
    **
    **  This revision:  2008 May 22
    */
    @Test public void t_fal03() {
        assertEquals(iauFal03(0.80), 5.132369751108684150, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ f a l p 0 3
    **  - - - - - - - - -
    **
    **  Test iauFalp03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFalp03, assertEquals
    **
    **  This revision:  2008 May 22
    */
    @Test public void t_falp03() {
        assertEquals(iauFalp03(0.80), 6.226797973505507345, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ f a m a 0 3
    **  - - - - - - - - -
    **
    **  Test iauFama03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFama03, assertEquals
    **
    **  This revision:  2008 May 22
    */
    @Test public void t_fama03() {
        assertEquals(iauFama03(0.80), 3.275506840277781492, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ f a m e 0 3
    **  - - - - - - - - -
    **
    **  Test iauFame03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFame03, assertEquals
    **
    **  This revision:  2008 May 22
    */
    @Test public void t_fame03() {
        assertEquals(iauFame03(0.80), 5.417338184297289661, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ f a n e 0 3
    **  - - - - - - - - -
    **
    **  Test iauFane03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFane03, assertEquals
    **
    **  This revision:  2008 May 22
    */
    @Test public void t_fane03() {
        assertEquals(iauFane03(0.80), 2.079343830860413523, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ f a o m 0 3
    **  - - - - - - - - -
    **
    **  Test iauFaom03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFaom03, assertEquals
    **
    **  This revision:  2008 May 22
    */
    @Test public void t_faom03() {
        assertEquals(iauFaom03(0.80), -5.973618440951302183, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ f a p a 0 3
    **  - - - - - - - - -
    **
    **  Test iauFapa03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFapa03, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_fapa03() {
        assertEquals(iauFapa03(0.80), 0.1950884762240000000e-1, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ f a s a 0 3
    **  - - - - - - - - -
    **
    **  Test iauFasa03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFasa03, assertEquals
    **
    **  This revision:  2008 May 22
    */
    @Test public void t_fasa03() {
        assertEquals(iauFasa03(0.80), 5.371574539440827046, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ f a u r 0 3
    **  - - - - - - - - -
    **
    **  Test iauFaur03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFaur03, assertEquals
    **
    **  This revision:  2008 May 22
    */
    @Test public void t_faur03() {
        assertEquals(iauFaur03(0.80), 5.180636450180413523, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ f a v e 0 3
    **  - - - - - - - - -
    **
    **  Test iauFave03 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFave03, assertEquals
    **
    **  This revision:  2008 May 22
    */
    @Test public void t_fave03() {
        assertEquals(iauFave03(0.80), 3.424900460533758000, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ f k 5 2 h
    **  - - - - - - - -
    **
    **  Test iauFk52h function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFk52h, assertEquals
    **
    **  This revision:  2009 November 6
    */
    @Test public void t_fk52h() {
        Pointer<Double> rh  = Pointer.allocateDouble();
        Pointer<Double> dh  = Pointer.allocateDouble();
        Pointer<Double> drh = Pointer.allocateDouble();
        Pointer<Double> ddh = Pointer.allocateDouble();
        Pointer<Double> pxh = Pointer.allocateDouble();
        Pointer<Double> rvh = Pointer.allocateDouble();

        double r5  =  1.76779433;
        double d5  = -0.2917517103;
        double dr5 = -1.91851572e-7;
        double dd5 = -5.8468475e-6;
        double px5 =  0.379210;
        double rv5 = -7.6;

        iauFk52h(r5, d5, dr5, dd5, px5, rv5,
                 rh, dh, drh, ddh, pxh, rvh);

        assertEquals(rh, 1.767794226299947632, 1e-14);
        assertEquals(dh,  -0.2917516070530391757, 1e-14);
        assertEquals(drh, -0.19618741256057224e-6,1e-19);
        assertEquals(ddh, -0.58459905176693911e-5, 1e-19);
        assertEquals(pxh,  0.37921, 1e-14);
        assertEquals(rvh, -7.6000000940000254, 1e-11);
    }

    /*
    **  - - - - - - - - -
    **   t _ f k 5 h i p
    **  - - - - - - - - -
    **
    **  Test iauFk5hip function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFk5hip, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_fk5hip() {
        Pointer<Pointer<Double>> r5h = Pointer.allocateDoubles(3, 3);
        Pointer<Double>          s5h = Pointer.allocateDoubles(3);

        iauFk5hip(r5h.get(), s5h);

        assertEquals(r5h.get(0).get(0), 0.9999999999999928638, 1e-14);
        assertEquals(r5h.get(0).get(1), 0.1110223351022919694e-6, 1e-17);
        assertEquals(r5h.get(0).get(2), 0.4411803962536558154e-7, 1e-17);
        assertEquals(r5h.get(1).get(0), -0.1110223308458746430e-6, 1e-17);
        assertEquals(r5h.get(1).get(1), 0.9999999999999891830, 1e-14);
        assertEquals(r5h.get(1).get(2), -0.9647792498984142358e-7, 1e-17);
        assertEquals(r5h.get(2).get(0), -0.4411805033656962252e-7, 1e-17);
        assertEquals(r5h.get(2).get(1), 0.9647792009175314354e-7, 1e-17);
        assertEquals(r5h.get(2).get(2), 0.9999999999999943728, 1e-14);
        assertEquals(s5h.get(0), -0.1454441043328607981e-8, 1e-17);
        assertEquals(s5h.get(1), 0.2908882086657215962e-8, 1e-17);
        assertEquals(s5h.get(2), 0.3393695767766751955e-8, 1e-17);
    }

    /*
    **  - - - - - - - -
    **   t _ f k 5 h z
    **  - - - - - - - -
    **
    **  Test iauFk5hz function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFk5hz, assertEquals
    **
    **  This revision:  2008 May 26
    */
    @Test public void t_fk5hz() {
        Pointer<Double> rh = Pointer.allocateDouble();
        Pointer<Double> dh = Pointer.allocateDouble();

        double r5 =  1.76779433;
        double d5 = -0.2917517103;

        iauFk5hz(r5, d5, 2400000.5, 54479.0, rh, dh);

        assertEquals(rh,  1.767794191464423978, 1e-12);
        assertEquals(dh, -0.2917516001679884419, 1e-12);
    }

    /*
    **  - - - - - - -
    **   t _ f w 2 m
    **  - - - - - - -
    **
    **  Test iauFw2m function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFw2m, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_fw2m() {
        Pointer<Pointer<Double>> r = Pointer.allocateDoubles(3, 3);

        double gamb = -0.2243387670997992368e-5;
        double phib =  0.4091014602391312982;
        double psi  = -0.9501954178013015092e-3;
        double eps  =  0.4091014316587367472;

        iauFw2m(gamb, phib, psi, eps, r.get());

        assertEquals(r.get(0).get(0), 0.9999995505176007047, 1e-12);
        assertEquals(r.get(0).get(1), 0.8695404617348192957e-3, 1e-12);
        assertEquals(r.get(0).get(2), 0.3779735201865582571e-3, 1e-12);

        assertEquals(r.get(1).get(0), -0.8695404723772016038e-3, 1e-12);
        assertEquals(r.get(1).get(1), 0.9999996219496027161, 1e-12);
        assertEquals(r.get(1).get(2), -0.1361752496887100026e-6, 1e-12);

        assertEquals(r.get(2).get(0), -0.3779734957034082790e-3, 1e-12);
        assertEquals(r.get(2).get(1), -0.1924880848087615651e-6, 1e-12);
        assertEquals(r.get(2).get(2), 0.9999999285679971958, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ f w 2 x y
    **  - - - - - - - -
    **
    **  Test iauFw2xy function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauFw2xy, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_fw2xy() {
        Pointer<Double> x = Pointer.allocateDouble();
        Pointer<Double> y = Pointer.allocateDouble();

        double gamb = -0.2243387670997992368e-5;
        double phib =  0.4091014602391312982;
        double psi  = -0.9501954178013015092e-3;
        double eps  =  0.4091014316587367472;

        iauFw2xy(gamb, phib, psi, eps, x, y);

        assertEquals(x, -0.3779734957034082790e-3, 1e-14);
        assertEquals(y, -0.1924880848087615651e-6, 1e-14);
    }

    /*
    **  - - - - - - - -
    **   t _ g c 2 g d
    **  - - - - - - - -
    **
    **  Test iauGc2gd function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGc2gd, assertEquals, assertEquals
    **
    **  This revision:  2012 February 23
    */
    @Test public void t_gc2gd() {
        Pointer<Double> xyz = Pointer.allocateDoubles(3);
        Pointer<Double> e = Pointer.allocateDouble();
        Pointer<Double> p = Pointer.allocateDouble();
        Pointer<Double> h = Pointer.allocateDouble();

        xyz.set(0, 2e6);
        xyz.set(1, 3e6);
        xyz.set(2, 5.244e6);

        int j = iauGc2gd(0, xyz, e, p, h);
        assertEquals(j, -1);

        j = iauGc2gd(WGS84, xyz, e, p, h);
        assertEquals(j, 0);
        assertEquals(e, 0.98279372324732907, 1e-14);
        assertEquals(p, 0.97160184819075459, 1e-14);
        assertEquals(h, 331.41724614260599, 1e-8);

        j = iauGc2gd(GRS80, xyz, e, p, h);
        assertEquals(j, 0);
        assertEquals(e, 0.98279372324732907, 1e-14);
        assertEquals(p, 0.97160184820607853, 1e-14);
        assertEquals(h, 331.41731754844348, 1e-8);

        j = iauGc2gd(WGS72, xyz, e, p, h);
        assertEquals(j, 0);
        assertEquals(e, 0.98279372324732907, 1e-14);
        assertEquals(p, 0.97160181811015119, 1e-14);
        assertEquals(h, 333.27707261303181, 1e-8);

        j = iauGc2gd(4, xyz, e, p, h);
        assertEquals(j, -1);
    }

    /*
    **  - - - - - - - - -
    **   t _ g c 2 g d e
    **  - - - - - - - - -
    **
    **  Test iauGc2gde function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGc2gde, assertEquals, assertEquals
    **
    **  This revision:  2010 September 7
    */
    @Test public void t_gc2gde() {
        Pointer<Double> xyz = Pointer.allocateDoubles(3);
        Pointer<Double> e = Pointer.allocateDouble();
        Pointer<Double> p = Pointer.allocateDouble();
        Pointer<Double> h = Pointer.allocateDouble();

        xyz.set(0, 2e6);
        xyz.set(1, 3e6);
        xyz.set(2, 5.244e6);

        double a = 6378136.0;
        double f = 0.0033528;

        int j = iauGc2gde(a, f, xyz, e, p, h);

        assertEquals(j, 0);
        assertEquals(e, 0.98279372324732907, 1e-14);
        assertEquals(p, 0.97160183775704115, 1e-14);
        assertEquals(h, 332.36862495764397, 1e-8);
    }

    /*
    **  - - - - - - - -
    **   t _ g d 2 g c
    **  - - - - - - - -
    **
    **  Test iauGd2gc function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGd2gc, assertEquals, assertEquals
    **
    **  This revision:  2012 February 23
    */
    @Test public void t_gd2gc() {
        Pointer<Double> xyz = Pointer.allocateDoubles(3);

        double e = 3.1;
        double p = -0.5;
        double h = 2500.0;

        int j = iauGd2gc(0, e, p, h, xyz);
        assertEquals(j, -1);

        j = iauGd2gc(WGS84, e, p, h, xyz);
        assertEquals(j, 0);
        assertEquals(xyz.get(0), -5599000.5577049947, 1e-7);
        assertEquals(xyz.get(1), 233011.67223479203, 1e-7);
        assertEquals(xyz.get(2), -3040909.4706983363, 1e-7);

        j = iauGd2gc(GRS80, e, p, h, xyz);
        assertEquals(j, 0);
        assertEquals(xyz.get(0), -5599000.5577260984, 1e-7);
        assertEquals(xyz.get(1), 233011.6722356703, 1e-7);
        assertEquals(xyz.get(2), -3040909.4706095476, 1e-7);

        j = iauGd2gc(WGS72, e, p, h, xyz);
        assertEquals(j, 0);
        assertEquals(xyz.get(0), -5598998.7626301490, 1e-7);
        assertEquals(xyz.get(1), 233011.5975297822, 1e-7);
        assertEquals(xyz.get(2), -3040908.6861467111, 1e-7);

        j = iauGd2gc(4, e, p, h, xyz);
        assertEquals(j, -1);
    }

    /*
    **  - - - - - - - - -
    **   t _ g d 2 g c e
    **  - - - - - - - - -
    **
    **  Test iauGd2gce function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGd2gce, assertEquals, assertEquals
    **
    **  This revision:  2010 September 7
    */
    @Test public void t_gd2gce() {
        double a = 6378136.0, f = 0.0033528;
        double e = 3.1, p = -0.5, h = 2500.0;
        Pointer<Double> xyz = Pointer.allocateDoubles(3);

        int j = iauGd2gce(a, f, e, p, h, xyz);

        assertEquals(j, 0);
        assertEquals(xyz.get(0), -5598999.6665116328, 1e-7);
        assertEquals(xyz.get(1), 233011.63514630572, 1e-7);
        assertEquals(xyz.get(2), -3040909.0517314132, 1e-7);
    }

    /*
    **  - - - - - - - - -
    **   t _ g m s t 0 0
    **  - - - - - - - - -
    **
    **  Test iauGmst00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGmst00, assertEquals
    **
    **  This revision:  2008 May 26
    */
    @Test public void t_gmst00() {
        double theta = iauGmst00(2400000.5, 53736.0, 2400000.5, 53736.0);
        assertEquals(theta, 1.754174972210740592, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ g m s t 0 6
    **  - - - - - - - - -
    **
    **  Test iauGmst06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGmst06, assertEquals
    **
    **  This revision:  2008 May 26
    */
    @Test public void t_gmst06() {
        double theta = iauGmst06(2400000.5, 53736.0, 2400000.5, 53736.0);
        assertEquals(theta, 1.754174971870091203, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ g m s t 8 2
    **  - - - - - - - - -
    **
    **  Test iauGmst82 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGmst82, assertEquals
    **
    **  This revision:  2008 May 26
    */
    @Test public void t_gmst82() {
        double theta = iauGmst82(2400000.5, 53736.0);
        assertEquals(theta, 1.754174981860675096, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ g s t 0 0 a
    **  - - - - - - - - -
    **
    **  Test iauGst00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGst00a, assertEquals
    **
    **  This revision:  2008 May 26
    */
    @Test public void t_gst00a() {
        double theta = iauGst00a(2400000.5, 53736.0, 2400000.5, 53736.0);
        assertEquals(theta, 1.754166138018281369, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ g s t 0 0 b
    **  - - - - - - - - -
    **
    **  Test iauGst00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGst00b, assertEquals
    **
    **  This revision:  2008 May 26
    */
    @Test public void t_gst00b() {
        double theta = iauGst00b(2400000.5, 53736.0);
        assertEquals(theta, 1.754166136510680589, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ g s t 0 6
    **  - - - - - - - -
    **
    **  Test iauGst06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGst06, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_gst06() {
        Pointer<Pointer<Double>> rnpb = Pointer.allocateDoubles(3, 3);

        rnpb.get(0).set(0,  0.9999989440476103608);
        rnpb.get(0).set(1, -0.1332881761240011518e-2);
        rnpb.get(0).set(2, -0.5790767434730085097e-3);

        rnpb.get(1).set(0,  0.1332858254308954453e-2);
        rnpb.get(1).set(1,  0.9999991109044505944);
        rnpb.get(1).set(2, -0.4097782710401555759e-4);

        rnpb.get(2).set(0,  0.5791308472168153320e-3);
        rnpb.get(2).set(1,  0.4020595661593994396e-4);
        rnpb.get(2).set(2,  0.9999998314954572365);

        double theta = iauGst06(2400000.5, 53736.0, 2400000.5, 53736.0, rnpb.get());

        assertEquals(theta, 1.754166138018167568, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ g s t 0 6 a
    **  - - - - - - - - -
    **
    **  Test iauGst06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGst06a, assertEquals
    **
    **  This revision:  2008 May 26
    */
    @Test public void t_gst06a() {
        double theta = iauGst06a(2400000.5, 53736.0, 2400000.5, 53736.0);
        assertEquals(theta, 1.754166137675019159, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ g s t 9 4
    **  - - - - - - - -
    **
    **  Test iauGst94 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauGst94, assertEquals
    **
    **  This revision:  2008 May 26
    */
    @Test public void t_gst94() {
        double theta = iauGst94(2400000.5, 53736.0);
        assertEquals(theta, 1.754166136020645203, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ h 2 f k 5
    **  - - - - - - - -
    **
    **  Test iauH2fk5 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauH2fk5, assertEquals
    **
    **  This revision:  2009 November 6
    */
    @Test public void t_h2fk5() {
        Pointer<Double> r5 = Pointer.allocateDouble();
        Pointer<Double> d5 = Pointer.allocateDouble();
        Pointer<Double> dr5 = Pointer.allocateDouble();
        Pointer<Double> dd5 = Pointer.allocateDouble();
        Pointer<Double> px5 = Pointer.allocateDouble();
        Pointer<Double> rv5 = Pointer.allocateDouble();

        double rh  =  1.767794352;
        double dh  = -0.2917512594;
        double drh = -2.76413026e-6;
        double ddh = -5.92994449e-6;
        double pxh =  0.379210;
        double rvh = -7.6;

        iauH2fk5(rh, dh, drh, ddh, pxh, rvh,
                 r5, d5, dr5, dd5, px5, rv5);

        assertEquals(r5, 1.767794455700065506, 1e-13);
        assertEquals(d5, -0.2917513626469638890, 1e-13);
        assertEquals(dr5, -0.27597945024511204e-5, 1e-18);
        assertEquals(dd5, -0.59308014093262838e-5, 1e-18);
        assertEquals(px5, 0.37921, 1e-13);
        assertEquals(rv5, -7.6000001309071126, 1e-10);
    }

    /*
    **  - - - - - - - -
    **   t _ h f k 5 z
    **  - - - - - - - -
    **
    **  Test iauHfk5z function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauHfk5z, assertEquals
    **
    **  This revision:  2008 November 29
    */
    @Test public void t_hfk5z() {
        Pointer<Double> r5 = Pointer.allocateDouble();
        Pointer<Double> d5 = Pointer.allocateDouble();
        Pointer<Double> dr5 = Pointer.allocateDouble();
        Pointer<Double> dd5 = Pointer.allocateDouble();

        double rh =  1.767794352;
        double dh = -0.2917512594;

        iauHfk5z(rh, dh, 2400000.5, 54479.0, r5, d5, dr5, dd5);

        assertEquals(r5, 1.767794490535581026, 1e-13);
        assertEquals(d5, -0.2917513695320114258, 1e-14);
        assertEquals(dr5, 0.4335890983539243029e-8, 1e-22);
        assertEquals(dd5, -0.8569648841237745902e-9, 1e-23);
    }

    /*
    **  - - - - -
    **   t _ i r
    **  - - - - -
    **
    **  Test iauIr function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauIr, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_ir() {
        Pointer<Pointer<Double>> r = Pointer.allocateDoubles(3, 3);

        r.get(0).set(0, 2.0);
        r.get(0).set(1, 3.0);
        r.get(0).set(2, 2.0);

        r.get(1).set(0, 3.0);
        r.get(1).set(1, 2.0);
        r.get(1).set(2, 3.0);

        r.get(2).set(0, 3.0);
        r.get(2).set(1, 4.0);
        r.get(2).set(2, 5.0);

        iauIr(r.get());

        assertEquals(r.get(0).get(0), 1.0, 0.0);
        assertEquals(r.get(0).get(1), 0.0, 0.0);
        assertEquals(r.get(0).get(2), 0.0, 0.0);

        assertEquals(r.get(1).get(0), 0.0, 0.0);
        assertEquals(r.get(1).get(1), 1.0, 0.0);
        assertEquals(r.get(1).get(2), 0.0, 0.0);

        assertEquals(r.get(2).get(0), 0.0, 0.0);
        assertEquals(r.get(2).get(1), 0.0, 0.0);
        assertEquals(r.get(2).get(2), 1.0, 0.0);
    }

    /*
    **  - - - - - - - - -
    **   t _ j d 2 c a l
    **  - - - - - - - - -
    **
    **  Test iauJd2cal function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauJd2cal, assertEquals, assertEquals
    **
    **  This revision:  2008 November 29
    */
    @Test public void t_jd2cal() {
        Pointer<Double>  fd = Pointer.allocateDouble();
        Pointer<Integer> iy = Pointer.allocateInt();
        Pointer<Integer> im = Pointer.allocateInt();
        Pointer<Integer> id = Pointer.allocateInt();

        double dj1 = 2400000.5;
        double dj2 = 50123.9999;

        int j = iauJd2cal(dj1, dj2, iy, im, id, fd);

        assertEquals(iy, 1996);
        assertEquals(im, 2);
        assertEquals(id, 10);
        assertEquals(fd, 0.9999, 1e-7);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - - - -
    **   t _ j d c a l f
    **  - - - - - - - - -
    **
    **  Test iauJdcalf function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauJdcalf, assertEquals
    **
    **  This revision:  2008 May 26
    */
    @Test public void t_jdcalf() {
        Pointer<Integer> iydmf = Pointer.allocateInts(4);

        double dj1 = 2400000.5;
        double dj2 = 50123.9999;

        int j = iauJdcalf(4, dj1, dj2, iydmf);

        assertEquals(iydmf.get(0), 1996);
        assertEquals(iydmf.get(1), 2);
        assertEquals(iydmf.get(2), 10);
        assertEquals(iydmf.get(3), 9999);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - - - -
    **   t _ n u m 0 0 a
    **  - - - - - - - - -
    **
    **  Test iauNum00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauNum00a, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_num00a() {
        Pointer<Pointer<Double>> rmatn = Pointer.allocateDoubles(3, 3);

        iauNum00a(2400000.5, 53736.0, rmatn.get());

        assertEquals(rmatn.get(0).get(0), 0.9999999999536227949, 1e-12);
        assertEquals(rmatn.get(0).get(1), 0.8836238544090873336e-5, 1e-12);
        assertEquals(rmatn.get(0).get(2), 0.3830835237722400669e-5, 1e-12);

        assertEquals(rmatn.get(1).get(0), -0.8836082880798569274e-5, 1e-12);
        assertEquals(rmatn.get(1).get(1), 0.9999999991354655028, 1e-12);
        assertEquals(rmatn.get(1).get(2), -0.4063240865362499850e-4, 1e-12);

        assertEquals(rmatn.get(2).get(0), -0.3831194272065995866e-5, 1e-12);
        assertEquals(rmatn.get(2).get(1), 0.4063237480216291775e-4, 1e-12);
        assertEquals(rmatn.get(2).get(2), 0.9999999991671660338, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ n u m 0 0 b
    **  - - - - - - - - -
    **
    **  Test iauNum00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauNum00b, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_num00b() {
        Pointer<Pointer<Double>> rmatn = Pointer.allocateDoubles(3, 3);

        iauNum00b(2400000.5, 53736, rmatn.get());

        assertEquals(rmatn.get(0).get(0), 0.9999999999536069682, 1e-12);
        assertEquals(rmatn.get(0).get(1), 0.8837746144871248011e-5, 1e-12);
        assertEquals(rmatn.get(0).get(2), 0.3831488838252202945e-5, 1e-12);

        assertEquals(rmatn.get(1).get(0), -0.8837590456632304720e-5, 1e-12);
        assertEquals(rmatn.get(1).get(1), 0.9999999991354692733, 1e-12);
        assertEquals(rmatn.get(1).get(2), -0.4063198798559591654e-4, 1e-12);

        assertEquals(rmatn.get(2).get(0), -0.3831847930134941271e-5, 1e-12);
        assertEquals(rmatn.get(2).get(1), 0.4063195412258168380e-4, 1e-12);
        assertEquals(rmatn.get(2).get(2), 0.9999999991671806225, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ n u m 0 6 a
    **  - - - - - - - - -
    **
    **  Test iauNum06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauNum06a, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_num06a() {
        Pointer<Pointer<Double>> rmatn = Pointer.allocateDoubles(3, 3);

        iauNum06a(2400000.5, 53736, rmatn.get());

        assertEquals(rmatn.get(0).get(0), 0.9999999999536227668, 1e-12);
        assertEquals(rmatn.get(0).get(1), 0.8836241998111535233e-5, 1e-12);
        assertEquals(rmatn.get(0).get(2), 0.3830834608415287707e-5, 1e-12);

        assertEquals(rmatn.get(1).get(0), -0.8836086334870740138e-5, 1e-12);
        assertEquals(rmatn.get(1).get(1), 0.9999999991354657474, 1e-12);
        assertEquals(rmatn.get(1).get(2), -0.4063240188248455065e-4, 1e-12);

        assertEquals(rmatn.get(2).get(0), -0.3831193642839398128e-5, 1e-12);
        assertEquals(rmatn.get(2).get(1), 0.4063236803101479770e-4, 1e-12);
        assertEquals(rmatn.get(2).get(2), 0.9999999991671663114, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ n u m a t
    **  - - - - - - - -
    **
    **  Test iauNumat function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauNumat, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_numat() {
        Pointer<Pointer<Double>> rmatn = Pointer.allocateDoubles(3, 3);

        double epsa =  0.4090789763356509900;
        double dpsi = -0.9630909107115582393e-5;
        double deps =  0.4063239174001678826e-4;

        iauNumat(epsa, dpsi, deps, rmatn.get());

        assertEquals(rmatn.get(0).get(0), 0.9999999999536227949, 1e-12);
        assertEquals(rmatn.get(0).get(1), 0.8836239320236250577e-5, 1e-12);
        assertEquals(rmatn.get(0).get(2), 0.3830833447458251908e-5, 1e-12);

        assertEquals(rmatn.get(1).get(0), -0.8836083657016688588e-5, 1e-12);
        assertEquals(rmatn.get(1).get(1), 0.9999999991354654959, 1e-12);
        assertEquals(rmatn.get(1).get(2), -0.4063240865361857698e-4, 1e-12);

        assertEquals(rmatn.get(2).get(0), -0.3831192481833385226e-5, 1e-12);
        assertEquals(rmatn.get(2).get(1), 0.4063237480216934159e-4, 1e-12);
        assertEquals(rmatn.get(2).get(2), 0.9999999991671660407, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ n u t 0 0 a
    **  - - - - - - - - -
    **
    **  Test iauNut00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauNut00a, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_nut00a() {
        Pointer<Double> dpsi = Pointer.allocateDouble();
        Pointer<Double> deps = Pointer.allocateDouble();

        iauNut00a(2400000.5, 53736.0, dpsi, deps);

        assertEquals(dpsi, -0.9630909107115518431e-5, 1e-13);
        assertEquals(deps,  0.4063239174001678710e-4, 1e-13);
    }

    /*
    **  - - - - - - - - -
    **   t _ n u t 0 0 b
    **  - - - - - - - - -
    **
    **  Test iauNut00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauNut00b, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_nut00b() {
        Pointer<Double> dpsi = Pointer.allocateDouble();
        Pointer<Double> deps = Pointer.allocateDouble();

        iauNut00b(2400000.5, 53736.0, dpsi, deps);

        assertEquals(dpsi, -0.9632552291148362783e-5, 1e-13);
        assertEquals(deps,  0.4063197106621159367e-4, 1e-13);
    }

    /*
    **  - - - - - - - - -
    **   t _ n u t 0 6 a
    **  - - - - - - - - -
    **
    **  Test iauNut06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauNut06a, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_nut06a() {
        Pointer<Double> dpsi = Pointer.allocateDouble();
        Pointer<Double> deps = Pointer.allocateDouble();

        iauNut06a(2400000.5, 53736.0, dpsi, deps);

        assertEquals(dpsi, -0.9630912025820308797e-5, 1e-13);
        assertEquals(deps,  0.4063238496887249798e-4, 1e-13);
    }

    /*
    **  - - - - - - - -
    **   t _ n u t 8 0
    **  - - - - - - - -
    **
    **  Test iauNut80 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauNut80, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_nut80() {
        Pointer<Double> dpsi = Pointer.allocateDouble();
        Pointer<Double> deps = Pointer.allocateDouble();

        iauNut80(2400000.5, 53736.0, dpsi, deps);

        assertEquals(dpsi, -0.9643658353226563966e-5, 1e-13);
        assertEquals(deps,  0.4060051006879713322e-4, 1e-13);
    }

    /*
    **  - - - - - - - - -
    **   t _ n u t m 8 0
    **  - - - - - - - - -
    **
    **  Test iauNutm80 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauNutm80, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_nutm80() {
        Pointer<Pointer<Double>> rmatn = Pointer.allocateDoubles(3, 3);

        iauNutm80(2400000.5, 53736.0, rmatn.get());

        assertEquals(rmatn.get(0).get(0), 0.9999999999534999268, 1e-12);
        assertEquals(rmatn.get(0).get(1), 0.8847935789636432161e-5, 1e-12);
        assertEquals(rmatn.get(0).get(2), 0.3835906502164019142e-5, 1e-12);

        assertEquals(rmatn.get(1).get(0), -0.8847780042583435924e-5, 1e-12);
        assertEquals(rmatn.get(1).get(1), 0.9999999991366569963, 1e-12);
        assertEquals(rmatn.get(1).get(2), -0.4060052702727130809e-4, 1e-12);

        assertEquals(rmatn.get(2).get(0), -0.3836265729708478796e-5, 1e-12);
        assertEquals(rmatn.get(2).get(1), 0.4060049308612638555e-4, 1e-12);
        assertEquals(rmatn.get(2).get(2), 0.9999999991684415129, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ o b l 0 6
    **  - - - - - - - -
    **
    **  Test iauObl06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauObl06, assertEquals
    **
    **  This revision:  2008 November 29
    */
    @Test public void t_obl06() {
        assertEquals(iauObl06(2400000.5, 54388.0), 0.4090749229387258204, 1e-14);
    }

    /*
    **  - - - - - - - -
    **   t _ o b l 8 0
    **  - - - - - - - -
    **
    **  Test iauObl80 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauObl80, assertEquals
    **
    **  This revision:  2008 November 29
    */
    @Test public void t_obl80() {
        double eps0 = iauObl80(2400000.5, 54388.0);
        assertEquals(eps0, 0.4090751347643816218, 1e-14);
    }

    /*
    **  - - - - - - -
    **   t _ p 0 6 e
    **  - - - - - - -
    **
    **  Test iauP06e function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauP06e, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_p06e() {
        Pointer<Double> eps0   = Pointer.allocateDouble();
        Pointer<Double> psia   = Pointer.allocateDouble();
        Pointer<Double> oma    = Pointer.allocateDouble();
        Pointer<Double> bpa    = Pointer.allocateDouble();
        Pointer<Double> bqa    = Pointer.allocateDouble();
        Pointer<Double> pia    = Pointer.allocateDouble();
        Pointer<Double> bpia   = Pointer.allocateDouble();
        Pointer<Double> epsa   = Pointer.allocateDouble();
        Pointer<Double> chia   = Pointer.allocateDouble();
        Pointer<Double> za     = Pointer.allocateDouble();
        Pointer<Double> zetaa  = Pointer.allocateDouble();
        Pointer<Double> thetaa = Pointer.allocateDouble();
        Pointer<Double> pa     = Pointer.allocateDouble();
        Pointer<Double> gam    = Pointer.allocateDouble();
        Pointer<Double> phi    = Pointer.allocateDouble();
        Pointer<Double> psi    = Pointer.allocateDouble();

        iauP06e(2400000.5, 52541.0, eps0, psia, oma, bpa,
                bqa, pia, bpia, epsa, chia, za,
                zetaa, thetaa, pa, gam, phi, psi);

        assertEquals(eps0, 0.4090926006005828715, 1e-14);
        assertEquals(psia, 0.6664369630191613431e-3, 1e-14);
        assertEquals(oma , 0.4090925973783255982, 1e-14);
        assertEquals(bpa, 0.5561149371265209445e-6, 1e-14);
        assertEquals(bqa, -0.6191517193290621270e-5, 1e-14);
        assertEquals(pia, 0.6216441751884382923e-5, 1e-14);
        assertEquals(bpia, 3.052014180023779882, 1e-14);
        assertEquals(epsa, 0.4090864054922431688, 1e-14);
        assertEquals(chia, 0.1387703379530915364e-5, 1e-14);
        assertEquals(za, 0.2921789846651790546e-3, 1e-14);
        assertEquals(zetaa, 0.3178773290332009310e-3, 1e-14);
        assertEquals(thetaa, 0.2650932701657497181e-3, 1e-14);
        assertEquals(pa, 0.6651637681381016344e-3, 1e-14);
        assertEquals(gam, 0.1398077115963754987e-5, 1e-14);
        assertEquals(phi, 0.4090864090837462602, 1e-14);
        assertEquals(psi, 0.6664464807480920325e-3, 1e-14);
    }

    /*
    **  - - - - - - -
    **   t _ p 2 p v
    **  - - - - - - -
    **
    **  Test iauP2pv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauP2pv, assertEquals
    **
    **  This revision:  2008 May 26
    */
    @Test public void t_p2pv() {
        Pointer<Double> p = Pointer.allocateDoubles(3);
        Pointer<Pointer<Double>> pv = Pointer.allocateDoubles(2, 3);

        p.set(0, 0.25);
        p.set(1, 1.2);
        p.set(2, 3.0);

        pv.get(0).set(0,  0.3);
        pv.get(0).set(1,  1.2);
        pv.get(0).set(2, -2.5);

        pv.get(1).set(0, -0.5);
        pv.get(1).set(1,  3.1);
        pv.get(1).set(2,  0.9);

        iauP2pv(p, pv.get());

        assertEquals(pv.get(0).get(0), 0.25, 0.0);
        assertEquals(pv.get(0).get(1), 1.2,  0.0);
        assertEquals(pv.get(0).get(2), 3.0,  0.0);

        assertEquals(pv.get(1).get(0), 0.0,  0.0);
        assertEquals(pv.get(1).get(1), 0.0,  0.0);
        assertEquals(pv.get(1).get(2), 0.0,  0.0);
    }

    /*
    **  - - - - - -
    **   t _ p 2 s
    **  - - - - - -
    **
    **  Test iauP2s function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauP2s, assertEquals
    **
    **  This revision:  2008 November 29
    */
    @Test public void t_p2s() {
        Pointer<Double> p = Pointer.allocateDoubles(3);
        Pointer<Double> theta = Pointer.allocateDouble();
        Pointer<Double> phi   = Pointer.allocateDouble();
        Pointer<Double> r     = Pointer.allocateDouble();

        p.set(0, 100.0);
        p.set(1, -50.0);
        p.set(2,  25.0);

        iauP2s(p, theta, phi, r);

        assertEquals(theta, -0.4636476090008061162, 1e-12);
        assertEquals(phi, 0.2199879773954594463, 1e-12);
        assertEquals(r, 114.5643923738960002, 1e-9);
    }

    /*
    **  - - - - - -
    **   t _ p a p
    **  - - - - - -
    **
    **  Test iauPap function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPap, assertEquals
    **
    **  This revision:  2008 May 25
    */
    @Test public void t_pap() {
        Pointer<Double> a = Pointer.allocateDoubles(3);
        Pointer<Double> b = Pointer.allocateDoubles(3);

        a.set(0,  1.0);
        a.set(1,  0.1);
        a.set(2,  0.2);

        b.set(0, -3.0);
        b.set(1, 1e-3);
        b.set(2,  0.2);

        double theta = iauPap(a, b);
        assertEquals(theta, 0.3671514267841113674, 1e-12);
    }

    /*
    **  - - - - - -
    **   t _ p a s
    **  - - - - - -
    **
    **  Test iauPas function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPas, assertEquals
    **
    **  This revision:  2008 May 25
    */
    @Test public void t_pas() {
        double al =  1.0;
        double ap =  0.1;
        double bl =  0.2;
        double bp = -1.0;

        double theta = iauPas(al, ap, bl, bp);
        assertEquals(theta, -2.724544922932270424, 1e-12);
    }

    /*
    **  - - - - - - -
    **   t _ p b 0 6
    **  - - - - - - -
    **
    **  Test iauPb06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPb06, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_pb06() {
        Pointer<Double> bzeta  = Pointer.allocateDouble();
        Pointer<Double> bz     = Pointer.allocateDouble();
        Pointer<Double> btheta = Pointer.allocateDouble();

        iauPb06(2400000.5, 50123.9999, bzeta, bz, btheta);

        assertEquals(bzeta, -0.5092634016326478238e-3, 1e-12);
        assertEquals(bz, -0.3602772060566044413e-3, 1e-12);
        assertEquals(btheta, -0.3779735537167811177e-3, 1e-12);
    }

    /*
    **  - - - - - -
    **   t _ p d p
    **  - - - - - -
    **
    **  Test iauPdp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPdp, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_pdp() {
        Pointer<Double> a = Pointer.allocateDoubles(3);
        Pointer<Double> b = Pointer.allocateDoubles(3);

        a.set(0, 2.0);
        a.set(1, 2.0);
        a.set(2, 3.0);

        b.set(0, 1.0);
        b.set(1, 3.0);
        b.set(2, 4.0);

        double adb = iauPdp(a, b);
        assertEquals(adb, 20, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ p f w 0 6
    **  - - - - - - - -
    **
    **  Test iauPfw06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPfw06, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_pfw06() {
        Pointer<Double> gamb = Pointer.allocateDouble();
        Pointer<Double> phib = Pointer.allocateDouble();
        Pointer<Double> psib = Pointer.allocateDouble();
        Pointer<Double> epsa = Pointer.allocateDouble();

        iauPfw06(2400000.5, 50123.9999, gamb, phib, psib, epsa);

        assertEquals(gamb, -0.2243387670997995690e-5, 1e-16);
        assertEquals(phib,  0.4091014602391312808, 1e-12);
        assertEquals(psib, -0.9501954178013031895e-3, 1e-14);
        assertEquals(epsa,  0.4091014316587367491, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ p l a n 9 4
    **  - - - - - - - - -
    **
    **  Test iauPlan94 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPlan94, assertEquals, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_plan94() {
        Pointer<Pointer<Double>> pv = Pointer.allocateDoubles(2, 3);

        int j = iauPlan94(2400000.5, 1e6, 0, pv.get());
        assertEquals(pv.get(0).get(0), 0.0, 0.0);
        assertEquals(pv.get(0).get(1), 0.0, 0.0);
        assertEquals(pv.get(0).get(2), 0.0, 0.0);
        assertEquals(pv.get(1).get(0), 0.0, 0.0);
        assertEquals(pv.get(1).get(1), 0.0, 0.0);
        assertEquals(pv.get(1).get(2), 0.0, 0.0);
        assertEquals(j, -1);

        j = iauPlan94(2400000.5, 1e6, 10, pv.get());
        assertEquals(j, -1);

        j = iauPlan94(2400000.5, -320000, 3, pv.get());
        assertEquals(pv.get(0).get(0), 0.9308038666832975759, 1e-11);
        assertEquals(pv.get(0).get(1), 0.3258319040261346000, 1e-11);
        assertEquals(pv.get(0).get(2), 0.1422794544481140560, 1e-11);
        assertEquals(pv.get(1).get(0), -0.6429458958255170006e-2, 1e-11);
        assertEquals(pv.get(1).get(1), 0.1468570657704237764e-1, 1e-11);
        assertEquals(pv.get(1).get(2), 0.6406996426270981189e-2, 1e-11);
        assertEquals(j, 1);

        j = iauPlan94(2400000.5, 43999.9, 1, pv.get());
        assertEquals(pv.get(0).get(0), 0.2945293959257430832, 1e-11);
        assertEquals(pv.get(0).get(1), -0.2452204176601049596, 1e-11);
        assertEquals(pv.get(0).get(2), -0.1615427700571978153, 1e-11);
        assertEquals(pv.get(1).get(0), 0.1413867871404614441e-1, 1e-11);
        assertEquals(pv.get(1).get(1), 0.1946548301104706582e-1, 1e-11);
        assertEquals(pv.get(1).get(2), 0.8929809783898904786e-2, 1e-11);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - - - -
    **   t _ p m a t 0 0
    **  - - - - - - - - -
    **
    **  Test iauPmat00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPmat00, assertEquals
    **
    **  This revision:  2008 November 29
    */
    @Test public void t_pmat00() {
        Pointer<Pointer<Double>> rbp = Pointer.allocateDoubles(3, 3);

        iauPmat00(2400000.5, 50123.9999, rbp.get());

        assertEquals(rbp.get(0).get(0), 0.9999995505175087260, 1e-12);
        assertEquals(rbp.get(0).get(1), 0.8695405883617884705e-3, 1e-14);
        assertEquals(rbp.get(0).get(2), 0.3779734722239007105e-3, 1e-14);

        assertEquals(rbp.get(1).get(0), -0.8695405990410863719e-3, 1e-14);
        assertEquals(rbp.get(1).get(1), 0.9999996219494925900, 1e-12);
        assertEquals(rbp.get(1).get(2), -0.1360775820404982209e-6, 1e-14);

        assertEquals(rbp.get(2).get(0), -0.3779734476558184991e-3, 1e-14);
        assertEquals(rbp.get(2).get(1), -0.1925857585832024058e-6, 1e-14);
        assertEquals(rbp.get(2).get(2), 0.9999999285680153377, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ p m a t 0 6
    **  - - - - - - - - -
    **
    **  Test iauPmat06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPmat06, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_pmat06() {
        Pointer<Pointer<Double>> rbp = Pointer.allocateDoubles(3, 3);

        iauPmat06(2400000.5, 50123.9999, rbp.get());

        assertEquals(rbp.get(0).get(0), 0.9999995505176007047, 1e-12);
        assertEquals(rbp.get(0).get(1), 0.8695404617348208406e-3, 1e-14);
        assertEquals(rbp.get(0).get(2), 0.3779735201865589104e-3, 1e-14);

        assertEquals(rbp.get(1).get(0), -0.8695404723772031414e-3, 1e-14);
        assertEquals(rbp.get(1).get(1), 0.9999996219496027161, 1e-12);
        assertEquals(rbp.get(1).get(2), -0.1361752497080270143e-6, 1e-14);

        assertEquals(rbp.get(2).get(0), -0.3779734957034089490e-3, 1e-14);
        assertEquals(rbp.get(2).get(1), -0.1924880847894457113e-6, 1e-14);
        assertEquals(rbp.get(2).get(2), 0.9999999285679971958, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ p m a t 7 6
    **  - - - - - - - - -
    **
    **  Test iauPmat76 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPmat76, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_pmat76() {
        Pointer<Pointer<Double>> rmatp = Pointer.allocateDoubles(3, 3);

        iauPmat76(2400000.5, 50123.9999, rmatp.get());

        assertEquals(rmatp.get(0).get(0), 0.9999995504328350733, 1e-12);
        assertEquals(rmatp.get(0).get(1), 0.8696632209480960785e-3, 1e-14);
        assertEquals(rmatp.get(0).get(2), 0.3779153474959888345e-3, 1e-14);

        assertEquals(rmatp.get(1).get(0), -0.8696632209485112192e-3, 1e-14);
        assertEquals(rmatp.get(1).get(1), 0.9999996218428560614, 1e-12);
        assertEquals(rmatp.get(1).get(2), -0.1643284776111886407e-6, 1e-14);

        assertEquals(rmatp.get(2).get(0), -0.3779153474950335077e-3, 1e-14);
        assertEquals(rmatp.get(2).get(1), -0.1643306746147366896e-6, 1e-14);
        assertEquals(rmatp.get(2).get(2), 0.9999999285899790119, 1e-12);
    }

    /*
    **  - - - - -
    **   t _ p m
    **  - - - - -
    **
    **  Test iauPm function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPm, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_pm() {
        Pointer<Double> p = Pointer.allocateDoubles(3);

        p.set(0,  0.3);
        p.set(1,  1.2);
        p.set(2, -2.5);

        double r = iauPm(p);
        assertEquals(r, 2.789265136196270604, 1e-12);
    }

    /*
    **  - - - - - -
    **   t _ p m p
    **  - - - - - -
    **
    **  Test iauPmp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPmp, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_pmp() {
        Pointer<Double> a   = Pointer.allocateDoubles(3);
        Pointer<Double> b   = Pointer.allocateDoubles(3);
        Pointer<Double> amb = Pointer.allocateDoubles(3);

        a.set(0, 2.0);
        a.set(1, 2.0);
        a.set(2, 3.0);

        b.set(0, 1.0);
        b.set(1, 3.0);
        b.set(2, 4.0);

        iauPmp(a, b, amb);

        assertEquals(amb.get(0),  1.0, 1e-12);
        assertEquals(amb.get(1), -1.0, 1e-12);
        assertEquals(amb.get(2), -1.0, 1e-12);
    }

    /*
    **  - - - - -
    **   t _ p n
    **  - - - - -
    **
    **  Test iauPn function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPn, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_pn() {
        Pointer<Double> p = Pointer.allocateDoubles(3);
        Pointer<Double> r = Pointer.allocateDouble();
        Pointer<Double> u = Pointer.allocateDoubles(3);

        p.set(0,  0.3);
        p.set(1,  1.2);
        p.set(2, -2.5);

        iauPn(p, r, u);

        assertEquals(r, 2.789265136196270604, 1e-12);

        assertEquals(u.get(0), 0.1075552109073112058, 1e-12);
        assertEquals(u.get(1), 0.4302208436292448232, 1e-12);
        assertEquals(u.get(2), -0.8962934242275933816, 1e-12);
    }

    /*
    **  - - - - - - -
    **   t _ p n 0 0
    **  - - - - - - -
    **
    **  Test iauPn00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPn00, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_pn00() {
        Pointer<Double> epsa = Pointer.allocateDouble();
        Pointer<Pointer<Double>> rb   = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rp   = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rbp  = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rn   = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rbpn = Pointer.allocateDoubles(3, 3);

        double dpsi = -0.9632552291149335877e-5;
        double deps =  0.4063197106621141414e-4;

        iauPn00(2400000.5, 53736.0, dpsi, deps, epsa, rb.get(), rp.get(), rbp.get(), rn.get(), rbpn.get());

        assertEquals(epsa, 0.4090791789404229916, 1e-12);

        assertEquals(rb.get(0).get(0), 0.9999999999999942498, 1e-12);
        assertEquals(rb.get(0).get(1), -0.7078279744199196626e-7, 1e-18);
        assertEquals(rb.get(0).get(2), 0.8056217146976134152e-7, 1e-18);

        assertEquals(rb.get(1).get(0), 0.7078279477857337206e-7, 1e-18);
        assertEquals(rb.get(1).get(1), 0.9999999999999969484, 1e-12);
        assertEquals(rb.get(1).get(2), 0.3306041454222136517e-7, 1e-18);

        assertEquals(rb.get(2).get(0), -0.8056217380986972157e-7, 1e-18);
        assertEquals(rb.get(2).get(1), -0.3306040883980552500e-7, 1e-18);
        assertEquals(rb.get(2).get(2), 0.9999999999999962084, 1e-12);

        assertEquals(rp.get(0).get(0), 0.9999989300532289018, 1e-12);
        assertEquals(rp.get(0).get(1), -0.1341647226791824349e-2, 1e-14);
        assertEquals(rp.get(0).get(2), -0.5829880927190296547e-3, 1e-14);

        assertEquals(rp.get(1).get(0), 0.1341647231069759008e-2, 1e-14);
        assertEquals(rp.get(1).get(1), 0.9999990999908750433, 1e-12);
        assertEquals(rp.get(1).get(2), -0.3837444441583715468e-6, 1e-14);

        assertEquals(rp.get(2).get(0), 0.5829880828740957684e-3, 1e-14);
        assertEquals(rp.get(2).get(1), -0.3984203267708834759e-6, 1e-14);
        assertEquals(rp.get(2).get(2), 0.9999998300623538046, 1e-12);

        assertEquals(rbp.get(0).get(0), 0.9999989300052243993, 1e-12);
        assertEquals(rbp.get(0).get(1), -0.1341717990239703727e-2, 1e-14);
        assertEquals(rbp.get(0).get(2), -0.5829075749891684053e-3, 1e-14);

        assertEquals(rbp.get(1).get(0), 0.1341718013831739992e-2, 1e-14);
        assertEquals(rbp.get(1).get(1), 0.9999990998959191343, 1e-12);
        assertEquals(rbp.get(1).get(2), -0.3505759733565421170e-6, 1e-14);

        assertEquals(rbp.get(2).get(0), 0.5829075206857717883e-3, 1e-14);
        assertEquals(rbp.get(2).get(1), -0.4315219955198608970e-6, 1e-14);
        assertEquals(rbp.get(2).get(2), 0.9999998301093036269, 1e-12);

        assertEquals(rn.get(0).get(0), 0.9999999999536069682, 1e-12);
        assertEquals(rn.get(0).get(1), 0.8837746144872140812e-5, 1e-16);
        assertEquals(rn.get(0).get(2), 0.3831488838252590008e-5, 1e-16);

        assertEquals(rn.get(1).get(0), -0.8837590456633197506e-5, 1e-16);
        assertEquals(rn.get(1).get(1), 0.9999999991354692733, 1e-12);
        assertEquals(rn.get(1).get(2), -0.4063198798559573702e-4, 1e-16);

        assertEquals(rn.get(2).get(0), -0.3831847930135328368e-5, 1e-16);
        assertEquals(rn.get(2).get(1), 0.4063195412258150427e-4, 1e-16);
        assertEquals(rn.get(2).get(2), 0.9999999991671806225, 1e-12);

        assertEquals(rbpn.get(0).get(0), 0.9999989440499982806, 1e-12);
        assertEquals(rbpn.get(0).get(1), -0.1332880253640848301e-2, 1e-14);
        assertEquals(rbpn.get(0).get(2), -0.5790760898731087295e-3, 1e-14);

        assertEquals(rbpn.get(1).get(0), 0.1332856746979948745e-2, 1e-14);
        assertEquals(rbpn.get(1).get(1), 0.9999991109064768883, 1e-12);
        assertEquals(rbpn.get(1).get(2), -0.4097740555723063806e-4, 1e-14);

        assertEquals(rbpn.get(2).get(0), 0.5791301929950205000e-3, 1e-14);
        assertEquals(rbpn.get(2).get(1), 0.4020553681373702931e-4, 1e-14);
        assertEquals(rbpn.get(2).get(2), 0.9999998314958529887, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ p n 0 0 a
    **  - - - - - - - -
    **
    **  Test iauPn00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPn00a, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_pn00a() {
        Pointer<Double> dpsi = Pointer.allocateDouble();
        Pointer<Double> deps = Pointer.allocateDouble();
        Pointer<Double> epsa = Pointer.allocateDouble();
        Pointer<Pointer<Double>> rb   = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rp   = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rbp  = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rn   = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rbpn = Pointer.allocateDoubles(3, 3);

        iauPn00a(2400000.5, 53736.0, dpsi, deps, epsa, rb.get(), rp.get(), rbp.get(), rn.get(), rbpn.get());

        assertEquals(dpsi, -0.9630909107115518431e-5, 1e-12);
        assertEquals(deps,  0.4063239174001678710e-4, 1e-12);
        assertEquals(epsa,  0.4090791789404229916, 1e-12);

        assertEquals(rb.get(0).get(0), 0.9999999999999942498, 1e-12);
        assertEquals(rb.get(0).get(1), -0.7078279744199196626e-7, 1e-16);
        assertEquals(rb.get(0).get(2), 0.8056217146976134152e-7, 1e-16);

        assertEquals(rb.get(1).get(0), 0.7078279477857337206e-7, 1e-16);
        assertEquals(rb.get(1).get(1), 0.9999999999999969484, 1e-12);
        assertEquals(rb.get(1).get(2), 0.3306041454222136517e-7, 1e-16);

        assertEquals(rb.get(2).get(0), -0.8056217380986972157e-7, 1e-16);
        assertEquals(rb.get(2).get(1), -0.3306040883980552500e-7, 1e-16);
        assertEquals(rb.get(2).get(2), 0.9999999999999962084, 1e-12);

        assertEquals(rp.get(0).get(0), 0.9999989300532289018, 1e-12);
        assertEquals(rp.get(0).get(1), -0.1341647226791824349e-2, 1e-14);
        assertEquals(rp.get(0).get(2), -0.5829880927190296547e-3, 1e-14);

        assertEquals(rp.get(1).get(0), 0.1341647231069759008e-2, 1e-14);
        assertEquals(rp.get(1).get(1), 0.9999990999908750433, 1e-12);
        assertEquals(rp.get(1).get(2), -0.3837444441583715468e-6, 1e-14);

        assertEquals(rp.get(2).get(0), 0.5829880828740957684e-3, 1e-14);
        assertEquals(rp.get(2).get(1), -0.3984203267708834759e-6, 1e-14);
        assertEquals(rp.get(2).get(2), 0.9999998300623538046, 1e-12);

        assertEquals(rbp.get(0).get(0), 0.9999989300052243993, 1e-12);
        assertEquals(rbp.get(0).get(1), -0.1341717990239703727e-2, 1e-14);
        assertEquals(rbp.get(0).get(2), -0.5829075749891684053e-3, 1e-14);

        assertEquals(rbp.get(1).get(0), 0.1341718013831739992e-2, 1e-14);
        assertEquals(rbp.get(1).get(1), 0.9999990998959191343, 1e-12);
        assertEquals(rbp.get(1).get(2), -0.3505759733565421170e-6, 1e-14);

        assertEquals(rbp.get(2).get(0), 0.5829075206857717883e-3, 1e-14);
        assertEquals(rbp.get(2).get(1), -0.4315219955198608970e-6, 1e-14);
        assertEquals(rbp.get(2).get(2), 0.9999998301093036269, 1e-12);

        assertEquals(rn.get(0).get(0), 0.9999999999536227949, 1e-12);
        assertEquals(rn.get(0).get(1), 0.8836238544090873336e-5, 1e-14);
        assertEquals(rn.get(0).get(2), 0.3830835237722400669e-5, 1e-14);

        assertEquals(rn.get(1).get(0), -0.8836082880798569274e-5, 1e-14);
        assertEquals(rn.get(1).get(1), 0.9999999991354655028, 1e-12);
        assertEquals(rn.get(1).get(2), -0.4063240865362499850e-4, 1e-14);

        assertEquals(rn.get(2).get(0), -0.3831194272065995866e-5, 1e-14);
        assertEquals(rn.get(2).get(1), 0.4063237480216291775e-4, 1e-14);
        assertEquals(rn.get(2).get(2), 0.9999999991671660338, 1e-12);

        assertEquals(rbpn.get(0).get(0), 0.9999989440476103435, 1e-12);
        assertEquals(rbpn.get(0).get(1), -0.1332881761240011763e-2, 1e-14);
        assertEquals(rbpn.get(0).get(2), -0.5790767434730085751e-3, 1e-14);

        assertEquals(rbpn.get(1).get(0), 0.1332858254308954658e-2, 1e-14);
        assertEquals(rbpn.get(1).get(1), 0.9999991109044505577, 1e-12);
        assertEquals(rbpn.get(1).get(2), -0.4097782710396580452e-4, 1e-14);

        assertEquals(rbpn.get(2).get(0), 0.5791308472168152904e-3, 1e-14);
        assertEquals(rbpn.get(2).get(1), 0.4020595661591500259e-4, 1e-14);
        assertEquals(rbpn.get(2).get(2), 0.9999998314954572304, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ p n 0 0 b
    **  - - - - - - - -
    **
    **  Test iauPn00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPn00b, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_pn00b() {
        Pointer<Double> dpsi = Pointer.allocateDouble();
        Pointer<Double> deps = Pointer.allocateDouble();
        Pointer<Double> epsa = Pointer.allocateDouble();
        Pointer<Pointer<Double>> rb   = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rp   = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rbp  = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rn   = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rbpn = Pointer.allocateDoubles(3, 3);

        iauPn00b(2400000.5, 53736.0, dpsi, deps, epsa, rb.get(), rp.get(), rbp.get(), rn.get(), rbpn.get());

        assertEquals(dpsi, -0.9632552291148362783e-5, 1e-12);
        assertEquals(deps,  0.4063197106621159367e-4, 1e-12);
        assertEquals(epsa,  0.4090791789404229916, 1e-12);

        assertEquals(rb.get(0).get(0), 0.9999999999999942498, 1e-12);
        assertEquals(rb.get(0).get(1), -0.7078279744199196626e-7, 1e-16);
        assertEquals(rb.get(0).get(2), 0.8056217146976134152e-7, 1e-16);

        assertEquals(rb.get(1).get(0), 0.7078279477857337206e-7, 1e-16);
        assertEquals(rb.get(1).get(1), 0.9999999999999969484, 1e-12);
        assertEquals(rb.get(1).get(2), 0.3306041454222136517e-7, 1e-16);

        assertEquals(rb.get(2).get(0), -0.8056217380986972157e-7, 1e-16);
        assertEquals(rb.get(2).get(1), -0.3306040883980552500e-7, 1e-16);
        assertEquals(rb.get(2).get(2), 0.9999999999999962084, 1e-12);

        assertEquals(rp.get(0).get(0), 0.9999989300532289018, 1e-12);
        assertEquals(rp.get(0).get(1), -0.1341647226791824349e-2, 1e-14);
        assertEquals(rp.get(0).get(2), -0.5829880927190296547e-3, 1e-14);

        assertEquals(rp.get(1).get(0), 0.1341647231069759008e-2, 1e-14);
        assertEquals(rp.get(1).get(1), 0.9999990999908750433, 1e-12);
        assertEquals(rp.get(1).get(2), -0.3837444441583715468e-6, 1e-14);

        assertEquals(rp.get(2).get(0), 0.5829880828740957684e-3, 1e-14);
        assertEquals(rp.get(2).get(1), -0.3984203267708834759e-6, 1e-14);
        assertEquals(rp.get(2).get(2), 0.9999998300623538046, 1e-12);

        assertEquals(rbp.get(0).get(0), 0.9999989300052243993, 1e-12);
        assertEquals(rbp.get(0).get(1), -0.1341717990239703727e-2, 1e-14);
        assertEquals(rbp.get(0).get(2), -0.5829075749891684053e-3, 1e-14);

        assertEquals(rbp.get(1).get(0), 0.1341718013831739992e-2, 1e-14);
        assertEquals(rbp.get(1).get(1), 0.9999990998959191343, 1e-12);
        assertEquals(rbp.get(1).get(2), -0.3505759733565421170e-6, 1e-14);

        assertEquals(rbp.get(2).get(0), 0.5829075206857717883e-3, 1e-14);
        assertEquals(rbp.get(2).get(1), -0.4315219955198608970e-6, 1e-14);
        assertEquals(rbp.get(2).get(2), 0.9999998301093036269, 1e-12);

        assertEquals(rn.get(0).get(0), 0.9999999999536069682, 1e-12);
        assertEquals(rn.get(0).get(1), 0.8837746144871248011e-5, 1e-14);
        assertEquals(rn.get(0).get(2), 0.3831488838252202945e-5, 1e-14);

        assertEquals(rn.get(1).get(0), -0.8837590456632304720e-5, 1e-14);
        assertEquals(rn.get(1).get(1), 0.9999999991354692733, 1e-12);
        assertEquals(rn.get(1).get(2), -0.4063198798559591654e-4, 1e-14);

        assertEquals(rn.get(2).get(0), -0.3831847930134941271e-5, 1e-14);
        assertEquals(rn.get(2).get(1), 0.4063195412258168380e-4, 1e-14);
        assertEquals(rn.get(2).get(2), 0.9999999991671806225, 1e-12);

        assertEquals(rbpn.get(0).get(0), 0.9999989440499982806, 1e-12);
        assertEquals(rbpn.get(0).get(1), -0.1332880253640849194e-2, 1e-14);
        assertEquals(rbpn.get(0).get(2), -0.5790760898731091166e-3, 1e-14);

        assertEquals(rbpn.get(1).get(0), 0.1332856746979949638e-2, 1e-14);
        assertEquals(rbpn.get(1).get(1), 0.9999991109064768883, 1e-12);
        assertEquals(rbpn.get(1).get(2), -0.4097740555723081811e-4, 1e-14);

        assertEquals(rbpn.get(2).get(0), 0.5791301929950208873e-3, 1e-14);
        assertEquals(rbpn.get(2).get(1), 0.4020553681373720832e-4, 1e-14);
        assertEquals(rbpn.get(2).get(2), 0.9999998314958529887, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ p n 0 6 a
    **  - - - - - - - -
    **
    **  Test iauPn06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPn06a, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_pn06a() {
        Pointer<Double> dpsi = Pointer.allocateDouble();
        Pointer<Double> deps = Pointer.allocateDouble();
        Pointer<Double> epsa = Pointer.allocateDouble();
        Pointer<Pointer<Double>> rb   = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rp   = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rbp  = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rn   = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rbpn = Pointer.allocateDoubles(3, 3);

        iauPn06a(2400000.5, 53736.0, dpsi, deps, epsa, rb.get(), rp.get(), rbp.get(), rn.get(), rbpn.get());

        assertEquals(dpsi, -0.9630912025820308797e-5, 1e-12);
        assertEquals(deps,  0.4063238496887249798e-4, 1e-12);
        assertEquals(epsa,  0.4090789763356509926, 1e-12);

        assertEquals(rb.get(0).get(0), 0.9999999999999942497, 1e-12);
        assertEquals(rb.get(0).get(1), -0.7078368960971557145e-7, 1e-14);
        assertEquals(rb.get(0).get(2), 0.8056213977613185606e-7, 1e-14);

        assertEquals(rb.get(1).get(0), 0.7078368694637674333e-7, 1e-14);
        assertEquals(rb.get(1).get(1), 0.9999999999999969484, 1e-12);
        assertEquals(rb.get(1).get(2), 0.3305943742989134124e-7, 1e-14);

        assertEquals(rb.get(2).get(0), -0.8056214211620056792e-7, 1e-14);
        assertEquals(rb.get(2).get(1), -0.3305943172740586950e-7, 1e-14);
        assertEquals(rb.get(2).get(2), 0.9999999999999962084, 1e-12);

        assertEquals(rp.get(0).get(0), 0.9999989300536854831, 1e-12);
        assertEquals(rp.get(0).get(1), -0.1341646886204443795e-2, 1e-14);
        assertEquals(rp.get(0).get(2), -0.5829880933488627759e-3, 1e-14);

        assertEquals(rp.get(1).get(0), 0.1341646890569782183e-2, 1e-14);
        assertEquals(rp.get(1).get(1), 0.9999990999913319321, 1e-12);
        assertEquals(rp.get(1).get(2), -0.3835944216374477457e-6, 1e-14);

        assertEquals(rp.get(2).get(0), 0.5829880833027867368e-3, 1e-14);
        assertEquals(rp.get(2).get(1), -0.3985701514686976112e-6, 1e-14);
        assertEquals(rp.get(2).get(2), 0.9999998300623534950, 1e-12);

        assertEquals(rbp.get(0).get(0), 0.9999989300056797893, 1e-12);
        assertEquals(rbp.get(0).get(1), -0.1341717650545059598e-2, 1e-14);
        assertEquals(rbp.get(0).get(2), -0.5829075756493728856e-3, 1e-14);

        assertEquals(rbp.get(1).get(0), 0.1341717674223918101e-2, 1e-14);
        assertEquals(rbp.get(1).get(1), 0.9999990998963748448, 1e-12);
        assertEquals(rbp.get(1).get(2), -0.3504269280170069029e-6, 1e-14);

        assertEquals(rbp.get(2).get(0), 0.5829075211461454599e-3, 1e-14);
        assertEquals(rbp.get(2).get(1), -0.4316708436255949093e-6, 1e-14);
        assertEquals(rbp.get(2).get(2), 0.9999998301093032943, 1e-12);

        assertEquals(rn.get(0).get(0), 0.9999999999536227668, 1e-12);
        assertEquals(rn.get(0).get(1), 0.8836241998111535233e-5, 1e-14);
        assertEquals(rn.get(0).get(2), 0.3830834608415287707e-5, 1e-14);

        assertEquals(rn.get(1).get(0), -0.8836086334870740138e-5, 1e-14);
        assertEquals(rn.get(1).get(1), 0.9999999991354657474, 1e-12);
        assertEquals(rn.get(1).get(2), -0.4063240188248455065e-4, 1e-14);

        assertEquals(rn.get(2).get(0), -0.3831193642839398128e-5, 1e-14);
        assertEquals(rn.get(2).get(1), 0.4063236803101479770e-4, 1e-14);
        assertEquals(rn.get(2).get(2), 0.9999999991671663114, 1e-12);

        assertEquals(rbpn.get(0).get(0), 0.9999989440480669738, 1e-12);
        assertEquals(rbpn.get(0).get(1), -0.1332881418091915973e-2, 1e-14);
        assertEquals(rbpn.get(0).get(2), -0.5790767447612042565e-3, 1e-14);

        assertEquals(rbpn.get(1).get(0), 0.1332857911250989133e-2, 1e-14);
        assertEquals(rbpn.get(1).get(1), 0.9999991109049141908, 1e-12);
        assertEquals(rbpn.get(1).get(2), -0.4097767128546784878e-4, 1e-14);

        assertEquals(rbpn.get(2).get(0), 0.5791308482835292617e-3, 1e-14);
        assertEquals(rbpn.get(2).get(1), 0.4020580099454020310e-4, 1e-14);
        assertEquals(rbpn.get(2).get(2), 0.9999998314954628695, 1e-12);
    }

    /*
    **  - - - - - - -
    **   t _ p n 0 6
    **  - - - - - - -
    **
    **  Test iauPn06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPn06, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_pn06() {
        Pointer<Double> epsa = Pointer.allocateDouble();
        Pointer<Pointer<Double>> rb   = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rp   = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rbp  = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rn   = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rbpn = Pointer.allocateDoubles(3, 3);

        double dpsi = -0.9632552291149335877e-5;
        double deps =  0.4063197106621141414e-4;

        iauPn06(2400000.5, 53736.0, dpsi, deps, epsa, rb.get(), rp.get(), rbp.get(), rn.get(), rbpn.get());

        assertEquals(epsa, 0.4090789763356509926, 1e-12);

        assertEquals(rb.get(0).get(0), 0.9999999999999942497, 1e-12);
        assertEquals(rb.get(0).get(1), -0.7078368960971557145e-7, 1e-14);
        assertEquals(rb.get(0).get(2), 0.8056213977613185606e-7, 1e-14);

        assertEquals(rb.get(1).get(0), 0.7078368694637674333e-7, 1e-14);
        assertEquals(rb.get(1).get(1), 0.9999999999999969484, 1e-12);
        assertEquals(rb.get(1).get(2), 0.3305943742989134124e-7, 1e-14);

        assertEquals(rb.get(2).get(0), -0.8056214211620056792e-7, 1e-14);
        assertEquals(rb.get(2).get(1), -0.3305943172740586950e-7, 1e-14);
        assertEquals(rb.get(2).get(2), 0.9999999999999962084, 1e-12);

        assertEquals(rp.get(0).get(0), 0.9999989300536854831, 1e-12);
        assertEquals(rp.get(0).get(1), -0.1341646886204443795e-2, 1e-14);
        assertEquals(rp.get(0).get(2), -0.5829880933488627759e-3, 1e-14);

        assertEquals(rp.get(1).get(0), 0.1341646890569782183e-2, 1e-14);
        assertEquals(rp.get(1).get(1), 0.9999990999913319321, 1e-12);
        assertEquals(rp.get(1).get(2), -0.3835944216374477457e-6, 1e-14);

        assertEquals(rp.get(2).get(0), 0.5829880833027867368e-3, 1e-14);
        assertEquals(rp.get(2).get(1), -0.3985701514686976112e-6, 1e-14);
        assertEquals(rp.get(2).get(2), 0.9999998300623534950, 1e-12);

        assertEquals(rbp.get(0).get(0), 0.9999989300056797893, 1e-12);
        assertEquals(rbp.get(0).get(1), -0.1341717650545059598e-2, 1e-14);
        assertEquals(rbp.get(0).get(2), -0.5829075756493728856e-3, 1e-14);

        assertEquals(rbp.get(1).get(0), 0.1341717674223918101e-2, 1e-14);
        assertEquals(rbp.get(1).get(1), 0.9999990998963748448, 1e-12);
        assertEquals(rbp.get(1).get(2), -0.3504269280170069029e-6, 1e-14);

        assertEquals(rbp.get(2).get(0), 0.5829075211461454599e-3, 1e-14);
        assertEquals(rbp.get(2).get(1), -0.4316708436255949093e-6, 1e-14);
        assertEquals(rbp.get(2).get(2), 0.9999998301093032943, 1e-12);

        assertEquals(rn.get(0).get(0), 0.9999999999536069682, 1e-12);
        assertEquals(rn.get(0).get(1), 0.8837746921149881914e-5, 1e-14);
        assertEquals(rn.get(0).get(2), 0.3831487047682968703e-5, 1e-14);

        assertEquals(rn.get(1).get(0), -0.8837591232983692340e-5, 1e-14);
        assertEquals(rn.get(1).get(1), 0.9999999991354692664, 1e-12);
        assertEquals(rn.get(1).get(2), -0.4063198798558931215e-4, 1e-14);

        assertEquals(rn.get(2).get(0), -0.3831846139597250235e-5, 1e-14);
        assertEquals(rn.get(2).get(1), 0.4063195412258792914e-4, 1e-14);
        assertEquals(rn.get(2).get(2), 0.9999999991671806293, 1e-12);

        assertEquals(rbpn.get(0).get(0), 0.9999989440504506688, 1e-12);
        assertEquals(rbpn.get(0).get(1), -0.1332879913170492655e-2, 1e-14);
        assertEquals(rbpn.get(0).get(2), -0.5790760923225655753e-3, 1e-14);

        assertEquals(rbpn.get(1).get(0), 0.1332856406595754748e-2, 1e-14);
        assertEquals(rbpn.get(1).get(1), 0.9999991109069366795, 1e-12);
        assertEquals(rbpn.get(1).get(2), -0.4097725651142641812e-4, 1e-14);

        assertEquals(rbpn.get(2).get(0), 0.5791301952321296716e-3, 1e-14);
        assertEquals(rbpn.get(2).get(1), 0.4020538796195230577e-4, 1e-14);
        assertEquals(rbpn.get(2).get(2), 0.9999998314958576778, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ p n m 0 0 a
    **  - - - - - - - - -
    **
    **  Test iauPnm00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPnm00a, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_pnm00a() {
        Pointer<Pointer<Double>> rbpn = Pointer.allocateDoubles(3, 3);

        iauPnm00a(2400000.5, 50123.9999, rbpn.get());

        assertEquals(rbpn.get(0).get(0), 0.9999995832793134257, 1e-12);
        assertEquals(rbpn.get(0).get(1), 0.8372384254137809439e-3, 1e-14);
        assertEquals(rbpn.get(0).get(2), 0.3639684306407150645e-3, 1e-14);

        assertEquals(rbpn.get(1).get(0), -0.8372535226570394543e-3, 1e-14);
        assertEquals(rbpn.get(1).get(1), 0.9999996486491582471, 1e-12);
        assertEquals(rbpn.get(1).get(2), 0.4132915262664072381e-4, 1e-14);

        assertEquals(rbpn.get(2).get(0), -0.3639337004054317729e-3, 1e-14);
        assertEquals(rbpn.get(2).get(1), -0.4163386925461775873e-4, 1e-14);
        assertEquals(rbpn.get(2).get(2), 0.9999999329094390695, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ p n m 0 0 b
    **  - - - - - - - - -
    **
    **  Test iauPnm00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPnm00b, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_pnm00b() {
        Pointer<Pointer<Double>> rbpn = Pointer.allocateDoubles(3, 3);

        iauPnm00b(2400000.5, 50123.9999, rbpn.get());

        assertEquals(rbpn.get(0).get(0), 0.9999995832776208280, 1e-12);
        assertEquals(rbpn.get(0).get(1), 0.8372401264429654837e-3, 1e-14);
        assertEquals(rbpn.get(0).get(2), 0.3639691681450271771e-3, 1e-14);

        assertEquals(rbpn.get(1).get(0), -0.8372552234147137424e-3, 1e-14);
        assertEquals(rbpn.get(1).get(1), 0.9999996486477686123, 1e-12);
        assertEquals(rbpn.get(1).get(2), 0.4132832190946052890e-4, 1e-14);

        assertEquals(rbpn.get(2).get(0), -0.3639344385341866407e-3, 1e-14);
        assertEquals(rbpn.get(2).get(1), -0.4163303977421522785e-4, 1e-14);
        assertEquals(rbpn.get(2).get(2), 0.9999999329092049734, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ p n m 0 6 a
    **  - - - - - - - - -
    **
    **  Test iauPnm06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPnm06a, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_pnm06a() {
        Pointer<Pointer<Double>> rbpn = Pointer.allocateDoubles(3, 3);

        iauPnm06a(2400000.5, 50123.9999, rbpn.get());

        assertEquals(rbpn.get(0).get(0), 0.9999995832794205484, 1e-12);
        assertEquals(rbpn.get(0).get(1), 0.8372382772630962111e-3, 1e-14);
        assertEquals(rbpn.get(0).get(2), 0.3639684771140623099e-3, 1e-14);

        assertEquals(rbpn.get(1).get(0), -0.8372533744743683605e-3, 1e-14);
        assertEquals(rbpn.get(1).get(1), 0.9999996486492861646, 1e-12);
        assertEquals(rbpn.get(1).get(2), 0.4132905944611019498e-4, 1e-14);

        assertEquals(rbpn.get(2).get(0), -0.3639337469629464969e-3, 1e-14);
        assertEquals(rbpn.get(2).get(1), -0.4163377605910663999e-4, 1e-14);
        assertEquals(rbpn.get(2).get(2), 0.9999999329094260057, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ p n m 8 0
    **  - - - - - - - -
    **
    **  Test iauPnm80 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPnm80, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_pnm80() {
        Pointer<Pointer<Double>> rmatpn = Pointer.allocateDoubles(3, 3);

        iauPnm80(2400000.5, 50123.9999, rmatpn.get());

        assertEquals(rmatpn.get(0).get(0), 0.9999995831934611169, 1e-12);
        assertEquals(rmatpn.get(0).get(1), 0.8373654045728124011e-3, 1e-14);
        assertEquals(rmatpn.get(0).get(2), 0.3639121916933106191e-3, 1e-14);

        assertEquals(rmatpn.get(1).get(0), -0.8373804896118301316e-3, 1e-14);
        assertEquals(rmatpn.get(1).get(1), 0.9999996485439674092, 1e-12);
        assertEquals(rmatpn.get(1).get(2), 0.4130202510421549752e-4, 1e-14);

        assertEquals(rmatpn.get(2).get(0), -0.3638774789072144473e-3, 1e-14);
        assertEquals(rmatpn.get(2).get(1), -0.4160674085851722359e-4, 1e-14);
        assertEquals(rmatpn.get(2).get(2), 0.9999999329310274805, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ p o m 0 0
    **  - - - - - - - -
    **
    **  Test iauPom00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPom00, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_pom00() {
        Pointer<Pointer<Double>> rpom = Pointer.allocateDoubles(3, 3);

        double xp =  2.55060238e-7;
        double yp =  1.860359247e-6;
        double sp = -0.1367174580728891460e-10;

        iauPom00(xp, yp, sp, rpom.get());

        assertEquals(rpom.get(0).get(0), 0.9999999999999674721, 1e-12);
        assertEquals(rpom.get(0).get(1), -0.1367174580728846989e-10, 1e-16);
        assertEquals(rpom.get(0).get(2), 0.2550602379999972345e-6, 1e-16);

        assertEquals(rpom.get(1).get(0), 0.1414624947957029801e-10, 1e-16);
        assertEquals(rpom.get(1).get(1), 0.9999999999982695317, 1e-12);
        assertEquals(rpom.get(1).get(2), -0.1860359246998866389e-5, 1e-16);

        assertEquals(rpom.get(2).get(0), -0.2550602379741215021e-6, 1e-16);
        assertEquals(rpom.get(2).get(1), 0.1860359247002414021e-5, 1e-16);
        assertEquals(rpom.get(2).get(2), 0.9999999999982370039, 1e-12);
    }

    /*
    **  - - - - - -
    **   t _ p p p
    **  - - - - - -
    **
    **  Test iauPpp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPpp, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_ppp() {
        Pointer<Double> a   = Pointer.allocateDoubles(3);
        Pointer<Double> b   = Pointer.allocateDoubles(3);
        Pointer<Double> apb = Pointer.allocateDoubles(3);

        a.set(0, 2.0);
        a.set(1, 2.0);
        a.set(2, 3.0);

        b.set(0, 1.0);
        b.set(1, 3.0);
        b.set(2, 4.0);

        iauPpp(a, b, apb);

        assertEquals(apb.get(0), 3.0, 1e-12);
        assertEquals(apb.get(1), 5.0, 1e-12);
        assertEquals(apb.get(2), 7.0, 1e-12);
    }

    /*
    **  - - - - - - -
    **   t _ p p s p
    **  - - - - - - -
    **
    **  Test iauPpsp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPpsp, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_ppsp() {
        Pointer<Double> a    = Pointer.allocateDoubles(3);
        Pointer<Double> b    = Pointer.allocateDoubles(3);
        Pointer<Double> apsb = Pointer.allocateDoubles(3);

        a.set(0, 2.0);
        a.set(1, 2.0);
        a.set(2, 3.0);

        double s = 5.0;

        b.set(0, 1.0);
        b.set(1, 3.0);
        b.set(2, 4.0);

        iauPpsp(a, s, b, apsb);

        assertEquals(apsb.get(0), 7.0, 1e-12);
        assertEquals(apsb.get(1), 17.0, 1e-12);
        assertEquals(apsb.get(2), 23.0, 1e-12);
    }

    /*
    **  - - - - - - -
    **   t _ p r 0 0
    **  - - - - - - -
    **
    **  Test iauPr00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPr00, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_pr00() {
        Pointer<Double> dpsipr = Pointer.allocateDouble();
        Pointer<Double> depspr = Pointer.allocateDouble();

        iauPr00(2400000.5, 53736, dpsipr, depspr);

        assertEquals(dpsipr, -0.8716465172668347629e-7, 1e-22);
        assertEquals(depspr, -0.7342018386722813087e-8, 1e-22);
    }

    /*
    **  - - - - - - - - -
    **   t _ p r e c 7 6
    **  - - - - - - - - -
    **
    **  Test iauPrec76 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPrec76, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_prec76() {
        Pointer<Double> zeta  = Pointer.allocateDouble();
        Pointer<Double> z     = Pointer.allocateDouble();
        Pointer<Double> theta = Pointer.allocateDouble();

        double ep01 = 2400000.5;
        double ep02 = 33282.0;
        double ep11 = 2400000.5;
        double ep12 = 51544.0;

        iauPrec76(ep01, ep02, ep11, ep12, zeta, z, theta);

        assertEquals(zeta,  0.5588961642000161243e-2, 1e-12);
        assertEquals(z,     0.5589922365870680624e-2, 1e-12);
        assertEquals(theta, 0.4858945471687296760e-2, 1e-12);
    }

    /*
    **  - - - - - - -
    **   t _ p v 2 p
    **  - - - - - - -
    **
    **  Test iauPv2p function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPv2p, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_pv2p() {
        Pointer<Double> p = Pointer.allocateDoubles(3);
        Pointer<Pointer<Double>> pv = Pointer.allocateDoubles(2, 3);

        pv.get(0).set(0,  0.3);
        pv.get(0).set(1,  1.2);
        pv.get(0).set(2, -2.5);

        pv.get(1).set(0, -0.5);
        pv.get(1).set(1,  3.1);
        pv.get(1).set(2,  0.9);

        iauPv2p(pv.get(), p);

        assertEquals(p.get(0),  0.3, 0.0);
        assertEquals(p.get(1),  1.2, 0.0);
        assertEquals(p.get(2), -2.5, 0.0);
    }

    /*
    **  - - - - - - -
    **   t _ p v 2 s
    **  - - - - - - -
    **
    **  Test iauPv2s function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPv2s, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_pv2s() {
        Pointer<Pointer<Double>> pv = Pointer.allocateDoubles(2, 3);
        Pointer<Double> theta = Pointer.allocateDouble();
        Pointer<Double> phi   = Pointer.allocateDouble();
        Pointer<Double> r     = Pointer.allocateDouble();
        Pointer<Double> td    = Pointer.allocateDouble();
        Pointer<Double> pd    = Pointer.allocateDouble();
        Pointer<Double> rd    = Pointer.allocateDouble();

        pv.get(0).set(0, -0.4514964673880165);
        pv.get(0).set(1,  0.03093394277342585);
        pv.get(0).set(2,  0.05594668105108779);

        pv.get(1).set(0,  1.292270850663260e-5);
        pv.get(1).set(1,  2.652814182060692e-6);
        pv.get(1).set(2,  2.568431853930293e-6);

        iauPv2s(pv.get(), theta, phi, r, td, pd, rd);

        assertEquals(theta, 3.073185307179586515, 1e-12);
        assertEquals(phi, 0.1229999999999999992, 1e-12);
        assertEquals(r, 0.4559999999999999757, 1e-12);
        assertEquals(td, -0.7800000000000000364e-5, 1e-16);
        assertEquals(pd, 0.9010000000000001639e-5, 1e-16);
        assertEquals(rd, -0.1229999999999999832e-4, 1e-16);
    }

    /*
    **  - - - - - - - -
    **   t _ p v d p v
    **  - - - - - - - -
    **
    **  Test iauPvdpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPvdpv, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_pvdpv() {
        Pointer<Pointer<Double>> a = Pointer.allocateDoubles(2, 3);
        Pointer<Pointer<Double>> b = Pointer.allocateDoubles(2, 3);
        Pointer<Double> adb = Pointer.allocateDoubles(2);

        a.get(0).set(0, 2.0);
        a.get(0).set(1, 2.0);
        a.get(0).set(2, 3.0);

        a.get(1).set(0, 6.0);
        a.get(1).set(1, 0.0);
        a.get(1).set(2, 4.0);

        b.get(0).set(0, 1.0);
        b.get(0).set(1, 3.0);
        b.get(0).set(2, 4.0);

        b.get(1).set(0, 0.0);
        b.get(1).set(1, 2.0);
        b.get(1).set(2, 8.0);

        iauPvdpv(a.get(), b.get(), adb);

        assertEquals(adb.get(0), 20.0, 1e-12);
        assertEquals(adb.get(1), 50.0, 1e-12);
    }

    /*
    **  - - - - - -
    **   t _ p v m
    **  - - - - - -
    **
    **  Test iauPvm function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPvm, assertEquals
    **
    **  This revision:  2008 May 25
    */
    @Test public void t_pvm() {
        Pointer<Pointer<Double>> pv = Pointer.allocateDoubles(2, 3);
        Pointer<Double> r = Pointer.allocateDouble();
        Pointer<Double> s = Pointer.allocateDouble();

        pv.get(0).set(0,  0.3);
        pv.get(0).set(1,  1.2);
        pv.get(0).set(2, -2.5);

        pv.get(1).set(0,  0.45);
        pv.get(1).set(1, -0.25);
        pv.get(1).set(2,  1.1);

        iauPvm(pv.get(), r, s);

        assertEquals(r, 2.789265136196270604, 1e-12);
        assertEquals(s, 1.214495780149111922, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ p v m p v
    **  - - - - - - - -
    **
    **  Test iauPvmpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPvmpv, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_pvmpv() {
        Pointer<Pointer<Double>> a   = Pointer.allocateDoubles(2, 3);
        Pointer<Pointer<Double>> b   = Pointer.allocateDoubles(2, 3);
        Pointer<Pointer<Double>> amb = Pointer.allocateDoubles(2, 3);

        a.get(0).set(0, 2.0);
        a.get(0).set(1, 2.0);
        a.get(0).set(2, 3.0);

        a.get(1).set(0, 5.0);
        a.get(1).set(1, 6.0);
        a.get(1).set(2, 3.0);

        b.get(0).set(0, 1.0);
        b.get(0).set(1, 3.0);
        b.get(0).set(2, 4.0);

        b.get(1).set(0, 3.0);
        b.get(1).set(1, 2.0);
        b.get(1).set(2, 1.0);

        iauPvmpv(a.get(), b.get(), amb.get());

        assertEquals(amb.get(0).get(0),  1.0, 1e-12);
        assertEquals(amb.get(0).get(1), -1.0, 1e-12);
        assertEquals(amb.get(0).get(2), -1.0, 1e-12);

        assertEquals(amb.get(1).get(0),  2.0, 1e-12);
        assertEquals(amb.get(1).get(1),  4.0, 1e-12);
        assertEquals(amb.get(1).get(2),  2.0, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ p v p p v
    **  - - - - - - - -
    **
    **  Test iauPvppv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPvppv, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_pvppv() {
        Pointer<Pointer<Double>> a   = Pointer.allocateDoubles(2, 3);
        Pointer<Pointer<Double>> b   = Pointer.allocateDoubles(2, 3);
        Pointer<Pointer<Double>> apb = Pointer.allocateDoubles(2, 3);

        a.get(0).set(0, 2.0);
        a.get(0).set(1, 2.0);
        a.get(0).set(2, 3.0);

        a.get(1).set(0, 5.0);
        a.get(1).set(1, 6.0);
        a.get(1).set(2, 3.0);

        b.get(0).set(0, 1.0);
        b.get(0).set(1, 3.0);
        b.get(0).set(2, 4.0);

        b.get(1).set(0, 3.0);
        b.get(1).set(1, 2.0);
        b.get(1).set(2, 1.0);

        iauPvppv(a.get(), b.get(), apb.get());

        assertEquals(apb.get(0).get(0), 3.0, 1e-12);
        assertEquals(apb.get(0).get(1), 5.0, 1e-12);
        assertEquals(apb.get(0).get(2), 7.0, 1e-12);

        assertEquals(apb.get(1).get(0), 8.0, 1e-12);
        assertEquals(apb.get(1).get(1), 8.0, 1e-12);
        assertEquals(apb.get(1).get(2), 4.0, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ p v s t a r
    **  - - - - - - - - -
    **
    **  Test iauPvstar function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPvstar, assertEquals, assertEquals
    **
    **  This revision:  2009 November 6
    */
    @Test public void t_pvstar() {
        Pointer<Pointer<Double>> pv = Pointer.allocateDoubles(2, 3);
        Pointer<Double> ra = Pointer.allocateDouble();
        Pointer<Double> dec = Pointer.allocateDouble();
        Pointer<Double> pmr = Pointer.allocateDouble();
        Pointer<Double> pmd = Pointer.allocateDouble();
        Pointer<Double> px = Pointer.allocateDouble();
        Pointer<Double> rv = Pointer.allocateDouble();

        pv.get(0).set(0,  126668.5912743160601);
        pv.get(0).set(1,  2136.792716839935195);
        pv.get(0).set(2, -245251.2339876830091);

        pv.get(1).set(0, -0.4051854035740712739e-2);
        pv.get(1).set(1, -0.6253919754866173866e-2);
        pv.get(1).set(2,  0.1189353719774107189e-1);

        int j = iauPvstar(pv.get(), ra, dec, pmr, pmd, px, rv);

        assertEquals(ra, 0.1686756e-1, 1e-12);
        assertEquals(dec, -1.093989828, 1e-12);
        assertEquals(pmr, -0.178323516e-4, 1e-16);
        assertEquals(pmd, 0.2336024047e-5, 1e-16);
        assertEquals(px, 0.74723, 1e-12);
        assertEquals(rv, -21.6, 1e-11);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - -
    **   t _ p v u
    **  - - - - - -
    **
    **  Test iauPvu function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPvu, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_pvu() {
        Pointer<Pointer<Double>> pv  = Pointer.allocateDoubles(2, 3);
        Pointer<Pointer<Double>> upv = Pointer.allocateDoubles(2, 3);

        pv.get(0).set(0,  126668.5912743160734);
        pv.get(0).set(1,  2136.792716839935565);
        pv.get(0).set(2, -245251.2339876830229);

        pv.get(1).set(0, -0.4051854035740713039e-2);
        pv.get(1).set(1, -0.6253919754866175788e-2);
        pv.get(1).set(2,  0.1189353719774107615e-1);

        iauPvu(2920.0, pv.get(), upv.get());

        assertEquals(upv.get(0).get(0), 126656.7598605317105, 1e-12);
        assertEquals(upv.get(0).get(1), 2118.531271155726332, 1e-12);
        assertEquals(upv.get(0).get(2), -245216.5048590656190, 1e-12);

        assertEquals(upv.get(1).get(0), -0.4051854035740713039e-2, 1e-12);
        assertEquals(upv.get(1).get(1), -0.6253919754866175788e-2, 1e-12);
        assertEquals(upv.get(1).get(2), 0.1189353719774107615e-1, 1e-12);
    }

    /*
    **  - - - - - - -
    **   t _ p v u p
    **  - - - - - - -
    **
    **  Test iauPvup function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPvup, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_pvup() {
        Pointer<Pointer<Double>> pv = Pointer.allocateDoubles(2, 3);
        Pointer<Double> p = Pointer.allocateDoubles(3);

        pv.get(0).set(0,  126668.5912743160734);
        pv.get(0).set(1,  2136.792716839935565);
        pv.get(0).set(2, -245251.2339876830229);

        pv.get(1).set(0, -0.4051854035740713039e-2);
        pv.get(1).set(1, -0.6253919754866175788e-2);
        pv.get(1).set(2,  0.1189353719774107615e-1);

        iauPvup(2920.0, pv.get(), p);

        assertEquals(p.get(0),  126656.7598605317105,   1e-12);
        assertEquals(p.get(1),    2118.531271155726332, 1e-12);
        assertEquals(p.get(2), -245216.5048590656190,   1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ p v x p v
    **  - - - - - - - -
    **
    **  Test iauPvxpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPvxpv, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_pvxpv() {
        Pointer<Pointer<Double>> a   = Pointer.allocateDoubles(2, 3);
        Pointer<Pointer<Double>> b   = Pointer.allocateDoubles(2, 3);
        Pointer<Pointer<Double>> axb = Pointer.allocateDoubles(2, 3);

        a.get(0).set(0, 2.0);
        a.get(0).set(1, 2.0);
        a.get(0).set(2, 3.0);

        a.get(1).set(0, 6.0);
        a.get(1).set(1, 0.0);
        a.get(1).set(2, 4.0);

        b.get(0).set(0, 1.0);
        b.get(0).set(1, 3.0);
        b.get(0).set(2, 4.0);

        b.get(1).set(0, 0.0);
        b.get(1).set(1, 2.0);
        b.get(1).set(2, 8.0);

        iauPvxpv(a.get(), b.get(), axb.get());

        assertEquals(axb.get(0).get(0),  -1.0, 1e-12);
        assertEquals(axb.get(0).get(1),  -5.0, 1e-12);
        assertEquals(axb.get(0).get(2),   4.0, 1e-12);

        assertEquals(axb.get(1).get(0),  -2.0, 1e-12);
        assertEquals(axb.get(1).get(1), -36.0, 1e-12);
        assertEquals(axb.get(1).get(2),  22.0, 1e-12);
    }

    /*
    **  - - - - - -
    **   t _ p x p
    **  - - - - - -
    **
    **  Test iauPxp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauPxp, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_pxp() {
        Pointer<Double> a   = Pointer.allocateDoubles(3);
        Pointer<Double> b   = Pointer.allocateDoubles(3);
        Pointer<Double> axb = Pointer.allocateDoubles(3);

        a.set(0, 2.0);
        a.set(1, 2.0);
        a.set(2, 3.0);

        b.set(0, 1.0);
        b.set(1, 3.0);
        b.set(2, 4.0);

        iauPxp(a, b, axb);

        assertEquals(axb.get(0), -1.0, 1e-12);
        assertEquals(axb.get(1), -5.0, 1e-12);
        assertEquals(axb.get(2),  4.0, 1e-12);
    }

    /*
    **  - - - - - - -
    **   t _ r m 2 v
    **  - - - - - - -
    **
    **  Test iauRm2v function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauRm2v, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_rm2v() {
        Pointer<Pointer<Double>> r = Pointer.allocateDoubles(3, 3);
        Pointer<Double> w = Pointer.allocateDoubles(3);

        r.get(0).set(0,  0.00);
        r.get(0).set(1, -0.80);
        r.get(0).set(2, -0.60);

        r.get(1).set(0,  0.80);
        r.get(1).set(1, -0.36);
        r.get(1).set(2,  0.48);

        r.get(2).set(0,  0.60);
        r.get(2).set(1,  0.48);
        r.get(2).set(2, -0.64);

        iauRm2v(r.get(), w);

        assertEquals(w.get(0),  0.0,                  1e-12);
        assertEquals(w.get(1),  1.413716694115406957, 1e-12);
        assertEquals(w.get(2), -1.884955592153875943, 1e-12);
    }

    /*
    **  - - - - - - -
    **   t _ r v 2 m
    **  - - - - - - -
    **
    **  Test iauRv2m function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauRv2m, assertEquals
    **
    **  This revision:  2008 May 27
    */
    @Test public void t_rv2m() {
        Pointer<Pointer<Double>> r = Pointer.allocateDoubles(3, 3);
        Pointer<Double> w = Pointer.allocateDoubles(3);

        w.set(0,  0.0);
        w.set(1,  1.41371669);
        w.set(2, -1.88495559);

        iauRv2m(w, r.get());

        assertEquals(r.get(0).get(0), -0.7071067782221119905, 1e-14);
        assertEquals(r.get(0).get(1), -0.5656854276809129651, 1e-14);
        assertEquals(r.get(0).get(2), -0.4242640700104211225, 1e-14);

        assertEquals(r.get(1).get(0),  0.5656854276809129651, 1e-14);
        assertEquals(r.get(1).get(1), -0.0925483394532274246, 1e-14);
        assertEquals(r.get(1).get(2), -0.8194112531408833269, 1e-14);

        assertEquals(r.get(2).get(0),  0.4242640700104211225, 1e-14);
        assertEquals(r.get(2).get(1), -0.8194112531408833269, 1e-14);
        assertEquals(r.get(2).get(2),  0.3854415612311154341, 1e-14);
    }

    /*
    **  - - - - -
    **   t _ r x
    **  - - - - -
    **
    **  Test iauRx function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauRx, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_rx() {
        Pointer<Pointer<Double>> r = Pointer.allocateDoubles(3, 3);

        double phi = 0.3456789;

        r.get(0).set(0, 2.0);
        r.get(0).set(1, 3.0);
        r.get(0).set(2, 2.0);

        r.get(1).set(0, 3.0);
        r.get(1).set(1, 2.0);
        r.get(1).set(2, 3.0);

        r.get(2).set(0, 3.0);
        r.get(2).set(1, 4.0);
        r.get(2).set(2, 5.0);

        iauRx(phi, r.get());

        assertEquals(r.get(0).get(0), 2.0, 0.0);
        assertEquals(r.get(0).get(1), 3.0, 0.0);
        assertEquals(r.get(0).get(2), 2.0, 0.0);

        assertEquals(r.get(1).get(0), 3.839043388235612460, 1e-12);
        assertEquals(r.get(1).get(1), 3.237033249594111899, 1e-12);
        assertEquals(r.get(1).get(2), 4.516714379005982719, 1e-12);

        assertEquals(r.get(2).get(0), 1.806030415924501684, 1e-12);
        assertEquals(r.get(2).get(1), 3.085711545336372503, 1e-12);
        assertEquals(r.get(2).get(2), 3.687721683977873065, 1e-12);
    }

    /*
    **  - - - - - -
    **   t _ r x p
    **  - - - - - -
    **
    **  Test iauRxp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauRxp, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_rxp() {
        Pointer<Pointer<Double>> r = Pointer.allocateDoubles(3, 3);
        Pointer<Double> p  = Pointer.allocateDoubles(3);
        Pointer<Double> rp = Pointer.allocateDoubles(3);

        r.get(0).set(0, 2.0);
        r.get(0).set(1, 3.0);
        r.get(0).set(2, 2.0);

        r.get(1).set(0, 3.0);
        r.get(1).set(1, 2.0);
        r.get(1).set(2, 3.0);

        r.get(2).set(0, 3.0);
        r.get(2).set(1, 4.0);
        r.get(2).set(2, 5.0);

        p.set(0, 0.2);
        p.set(1, 1.5);
        p.set(2, 0.1);

        iauRxp(r.get(), p, rp);

        assertEquals(rp.get(0), 5.1, 1e-12);
        assertEquals(rp.get(1), 3.9, 1e-12);
        assertEquals(rp.get(2), 7.1, 1e-12);

    }

    /*
    **  - - - - - - -
    **   t _ r x p v
    **  - - - - - - -
    **
    **  Test iauRxpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauRxpv, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_rxpv() {
        Pointer<Pointer<Double>> r   = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> pv  = Pointer.allocateDoubles(2, 3);
        Pointer<Pointer<Double>> rpv = Pointer.allocateDoubles(2, 3);

        r.get(0).set(0, 2.0);
        r.get(0).set(1, 3.0);
        r.get(0).set(2, 2.0);

        r.get(1).set(0, 3.0);
        r.get(1).set(1, 2.0);
        r.get(1).set(2, 3.0);

        r.get(2).set(0, 3.0);
        r.get(2).set(1, 4.0);
        r.get(2).set(2, 5.0);

        pv.get(0).set(0, 0.2);
        pv.get(0).set(1, 1.5);
        pv.get(0).set(2, 0.1);

        pv.get(1).set(0, 1.5);
        pv.get(1).set(1, 0.2);
        pv.get(1).set(2, 0.1);

        iauRxpv(r.get(), pv.get(), rpv.get());

        assertEquals(rpv.get(0).get(0), 5.1, 1e-12);
        assertEquals(rpv.get(1).get(0), 3.8, 1e-12);

        assertEquals(rpv.get(0).get(1), 3.9, 1e-12);
        assertEquals(rpv.get(1).get(1), 5.2, 1e-12);

        assertEquals(rpv.get(0).get(2), 7.1, 1e-12);
        assertEquals(rpv.get(1).get(2), 5.8, 1e-12);
    }

    /*
    **  - - - - - -
    **   t _ r x r
    **  - - - - - -
    **
    **  Test iauRxr function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauRxr, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_rxr() {
        Pointer<Pointer<Double>> a   = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> b   = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> atb = Pointer.allocateDoubles(3, 3);

        a.get(0).set(0, 2.0);
        a.get(0).set(1, 3.0);
        a.get(0).set(2, 2.0);

        a.get(1).set(0, 3.0);
        a.get(1).set(1, 2.0);
        a.get(1).set(2, 3.0);

        a.get(2).set(0, 3.0);
        a.get(2).set(1, 4.0);
        a.get(2).set(2, 5.0);

        b.get(0).set(0, 1.0);
        b.get(0).set(1, 2.0);
        b.get(0).set(2, 2.0);

        b.get(1).set(0, 4.0);
        b.get(1).set(1, 1.0);
        b.get(1).set(2, 1.0);

        b.get(2).set(0, 3.0);
        b.get(2).set(1, 0.0);
        b.get(2).set(2, 1.0);

        iauRxr(a.get(), b.get(), atb.get());

        assertEquals(atb.get(0).get(0), 20.0, 1e-12);
        assertEquals(atb.get(0).get(1),  7.0, 1e-12);
        assertEquals(atb.get(0).get(2),  9.0, 1e-12);

        assertEquals(atb.get(1).get(0), 20.0, 1e-12);
        assertEquals(atb.get(1).get(1),  8.0, 1e-12);
        assertEquals(atb.get(1).get(2), 11.0, 1e-12);

        assertEquals(atb.get(2).get(0), 34.0, 1e-12);
        assertEquals(atb.get(2).get(1), 10.0, 1e-12);
        assertEquals(atb.get(2).get(2), 15.0, 1e-12);
    }

    /*
    **  - - - - -
    **   t _ r y
    **  - - - - -
    **
    **  Test iauRy function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauRy, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_ry() {
        Pointer<Pointer<Double>> r = Pointer.allocateDoubles(3, 3);

        double theta = 0.3456789;

        r.get(0).set(0, 2.0);
        r.get(0).set(1, 3.0);
        r.get(0).set(2, 2.0);

        r.get(1).set(0, 3.0);
        r.get(1).set(1, 2.0);
        r.get(1).set(2, 3.0);

        r.get(2).set(0, 3.0);
        r.get(2).set(1, 4.0);
        r.get(2).set(2, 5.0);

        iauRy(theta, r.get());

        assertEquals(r.get(0).get(0), 0.8651847818978159930, 1e-12);
        assertEquals(r.get(0).get(1), 1.467194920539316554, 1e-12);
        assertEquals(r.get(0).get(2), 0.1875137911274457342, 1e-12);

        assertEquals(r.get(1).get(0), 3, 1e-12);
        assertEquals(r.get(1).get(1), 2, 1e-12);
        assertEquals(r.get(1).get(2), 3, 1e-12);

        assertEquals(r.get(2).get(0), 3.500207892850427330, 1e-12);
        assertEquals(r.get(2).get(1), 4.779889022262298150, 1e-12);
        assertEquals(r.get(2).get(2), 5.381899160903798712, 1e-12);
    }

    /*
    **  - - - - -
    **   t _ r z
    **  - - - - -
    **
    **  Test iauRz function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauRz, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_rz() {
        Pointer<Pointer<Double>> r = Pointer.allocateDoubles(3, 3);

        double psi = 0.3456789;

        r.get(0).set(0, 2.0);
        r.get(0).set(1, 3.0);
        r.get(0).set(2, 2.0);

        r.get(1).set(0, 3.0);
        r.get(1).set(1, 2.0);
        r.get(1).set(2, 3.0);

        r.get(2).set(0, 3.0);
        r.get(2).set(1, 4.0);
        r.get(2).set(2, 5.0);

        iauRz(psi, r.get());

        assertEquals(r.get(0).get(0), 2.898197754208926769, 1e-12);
        assertEquals(r.get(0).get(1), 3.500207892850427330, 1e-12);
        assertEquals(r.get(0).get(2), 2.898197754208926769, 1e-12);

        assertEquals(r.get(1).get(0), 2.144865911309686813, 1e-12);
        assertEquals(r.get(1).get(1), 0.865184781897815993, 1e-12);
        assertEquals(r.get(1).get(2), 2.144865911309686813, 1e-12);

        assertEquals(r.get(2).get(0), 3.0, 1e-12);
        assertEquals(r.get(2).get(1), 4.0, 1e-12);
        assertEquals(r.get(2).get(2), 5.0, 1e-12);
    }

    /*
    **  - - - - - - -
    **   t _ s 0 0 a
    **  - - - - - - -
    **
    **  Test iauS00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauS00a, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_s00a() {
        double s = iauS00a(2400000.5, 52541.0);
        assertEquals(s, -0.1340684448919163584e-7, 1e-18);
    }

    /*
    **  - - - - - - -
    **   t _ s 0 0 b
    **  - - - - - - -
    **
    **  Test iauS00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauS00b, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_s00b() {
        double s = iauS00b(2400000.5, 52541.0);
        assertEquals(s, -0.1340695782951026584e-7, 1e-18);
    }

    /*
    **  - - - - - -
    **   t _ s 0 0
    **  - - - - - -
    **
    **  Test iauS00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauS00, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_s00() {
        double x = 0.5791308486706011000e-3;
        double y = 0.4020579816732961219e-4;

        double s = iauS00(2400000.5, 53736.0, x, y);
        assertEquals(s, -0.1220036263270905693e-7, 1e-18);
    }

    /*
    **  - - - - - - -
    **   t _ s 0 6 a
    **  - - - - - - -
    **
    **  Test iauS06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauS06a, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_s06a() {
        double s = iauS06a(2400000.5, 52541.0);
        assertEquals(s, -0.1340680437291812383e-7, 1e-18);
    }

    /*
    **  - - - - - -
    **   t _ s 0 6
    **  - - - - - -
    **
    **  Test iauS06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauS06, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_s06() {
        double x = 0.5791308486706011000e-3;
        double y = 0.4020579816732961219e-4;

        double s = iauS06(2400000.5, 53736.0, x, y);
        assertEquals(s, -0.1220032213076463117e-7, 1e-18);
    }

    /*
    **  - - - - - -
    **   t _ s 2 c
    **  - - - - - -
    **
    **  Test iauS2c function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauS2c, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_s2c() {
        Pointer<Double> c = Pointer.allocateDoubles(3);

        iauS2c(3.0123, -0.999, c);

        assertEquals(c.get(0), -0.5366267667260523906, 1e-12);
        assertEquals(c.get(1),  0.0697711109765145365, 1e-12);
        assertEquals(c.get(2), -0.8409302618566214041, 1e-12);
    }

    /*
    **  - - - - - -
    **   t _ s 2 p
    **  - - - - - -
    **
    **  Test iauS2p function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauS2p, assertEquals
    **
    **  This revision:  2008 May 25
    */
    @Test public void t_s2p() {
        Pointer<Double> p = Pointer.allocateDoubles(3);

        iauS2p(-3.21, 0.123, 0.456, p);

        assertEquals(p.get(0), -0.4514964673880165228, 1e-12);
        assertEquals(p.get(1),  0.0309339427734258688, 1e-12);
        assertEquals(p.get(2),  0.0559466810510877933, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ s 2 x p v
    **  - - - - - - - -
    **
    **  Test iauS2xpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauS2xpv, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_s2xpv() {
        Pointer<Pointer<Double>> pv  = Pointer.allocateDoubles(2, 3);
        Pointer<Pointer<Double>> spv = Pointer.allocateDoubles(2, 3);

        double s1 = 2.0;
        double s2 = 3.0;

        pv.get(0).set(0,  0.3);
        pv.get(0).set(1,  1.2);
        pv.get(0).set(2, -2.5);

        pv.get(1).set(0,  0.5);
        pv.get(1).set(1,  2.3);
        pv.get(1).set(2, -0.4);

        iauS2xpv(s1, s2, pv.get(), spv.get());

        assertEquals(spv.get(0).get(0),  0.6, 1e-12);
        assertEquals(spv.get(0).get(1),  2.4, 1e-12);
        assertEquals(spv.get(0).get(2), -5.0, 1e-12);

        assertEquals(spv.get(1).get(0),  1.5, 1e-12);
        assertEquals(spv.get(1).get(1),  6.9, 1e-12);
        assertEquals(spv.get(1).get(2), -1.2, 1e-12);
    }

    /*
    **  - - - - - - -
    **   t _ s 2 p v
    **  - - - - - - -
    **
    **  Test iauS2pv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauS2pv, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_s2pv() {
        Pointer<Pointer<Double>> pv = Pointer.allocateDoubles(2, 3);

        iauS2pv(-3.21, 0.123, 0.456, -7.8e-6, 9.01e-6, -1.23e-5, pv.get());

        assertEquals(pv.get(0).get(0), -0.4514964673880165228, 1e-12);
        assertEquals(pv.get(0).get(1),  0.0309339427734258688, 1e-12);
        assertEquals(pv.get(0).get(2),  0.0559466810510877933, 1e-12);

        assertEquals(pv.get(1).get(0),  0.1292270850663260170e-4, 1e-16);
        assertEquals(pv.get(1).get(1),  0.2652814182060691422e-5, 1e-16);
        assertEquals(pv.get(1).get(2),  0.2568431853930292259e-5, 1e-16);
    }

    /*
    **  - - - - - - -
    **   t _ s e p p
    **  - - - - - - -
    **
    **  Test iauSepp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauSepp, assertEquals
    **
    **  This revision:  2008 November 29
    */
    @Test public void t_sepp() {
        Pointer<Double> a = Pointer.allocateDoubles(3);
        Pointer<Double> b = Pointer.allocateDoubles(3);

        a.set(0,  1.0);
        a.set(1,  0.1);
        a.set(2,  0.2);

        b.set(0, -3.0);
        b.set(1,  1e-3);
        b.set(2,  0.2);

        double s = iauSepp(a, b);
        assertEquals(s, 2.860391919024660768, 1e-12);
    }

    /*
    **  - - - - - - -
    **   t _ s e p s
    **  - - - - - - -
    **
    **  Test iauSeps function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauSeps, assertEquals
    **
    **  This revision:  2008 May 22
    */
    @Test public void t_seps() {
        double al =  1.0;
        double ap =  0.1;

        double bl =  0.2;
        double bp = -3.0;

        double s = iauSeps(al, ap, bl, bp);
        assertEquals(s, 2.346722016996998842, 1e-14);
    }

    /*
    **  - - - - - - -
    **   t _ s p 0 0
    **  - - - - - - -
    **
    **  Test iauSp00 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauSp00, assertEquals
    **
    **  This revision:  2008 May 25
    */
    @Test public void t_sp00() {
        assertEquals(iauSp00(2400000.5, 52541.0), -0.6216698469981019309e-11, 1e-12);
    }

    /*
    **  - - - - - - - - -
    **   t _ s t a r p m
    **  - - - - - - - - -
    **
    **  Test iauStarpm function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauStarpm, assertEquals, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_starpm() {
        Pointer<Double> ra2  = Pointer.allocateDouble();
        Pointer<Double> dec2 = Pointer.allocateDouble();
        Pointer<Double> pmr2 = Pointer.allocateDouble();
        Pointer<Double> pmd2 = Pointer.allocateDouble();
        Pointer<Double> px2  = Pointer.allocateDouble();
        Pointer<Double> rv2  = Pointer.allocateDouble();

        double ra1 =   0.01686756;
        double dec1 = -1.093989828;
        double pmr1 = -1.78323516e-5;
        double pmd1 =  2.336024047e-6;
        double px1 =   0.74723;
        double rv1 = -21.6;

        int j = iauStarpm(ra1, dec1, pmr1, pmd1, px1, rv1,
                            2400000.5, 50083.0, 2400000.5, 53736.0,
                            ra2, dec2, pmr2, pmd2, px2, rv2);

        assertEquals(ra2, 0.01668919069414242368, 1e-13);
        assertEquals(dec2, -1.093966454217127879, 1e-13);
        assertEquals(pmr2, -0.1783662682155932702e-4, 1e-17);
        assertEquals(pmd2, 0.2338092915987603664e-5, 1e-17);
        assertEquals(px2, 0.7473533835323493644, 1e-13);
        assertEquals(rv2, -21.59905170476860786, 1e-11);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - - - -
    **   t _ s t a r p v
    **  - - - - - - - - -
    **
    **  Test iauStarpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauStarpv, assertEquals, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_starpv() {
        Pointer<Pointer<Double>> pv  = Pointer.allocateDoubles(2, 3);

        double ra =   0.01686756;
        double dec = -1.093989828;
        double pmr = -1.78323516e-5;
        double pmd =  2.336024047e-6;
        double px =   0.74723;
        double rv = -21.6;

        int j = iauStarpv(ra, dec, pmr, pmd, px, rv, pv.get());

        assertEquals(pv.get(0).get(0), 126668.5912743160601, 1e-10);
        assertEquals(pv.get(0).get(1), 2136.792716839935195, 1e-12);
        assertEquals(pv.get(0).get(2), -245251.2339876830091, 1e-10);

        assertEquals(pv.get(1).get(0), -0.4051854035740712739e-2, 1e-13);
        assertEquals(pv.get(1).get(1), -0.6253919754866173866e-2, 1e-15);
        assertEquals(pv.get(1).get(2), 0.1189353719774107189e-1, 1e-13);

        assertEquals(j, 0);
    }

    /*
    **  - - - - - -
    **   t _ s x p
    **  - - - - - -
    **
    **  Test iauSxp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauSxp, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_sxp() {
        Pointer<Double> p  = Pointer.allocateDoubles(3);
        Pointer<Double> sp = Pointer.allocateDoubles(3);

        double s = 2.0;

        p.set(0,  0.3);
        p.set(1,  1.2);
        p.set(2, -2.5);

        iauSxp(s, p, sp);

        assertEquals(sp.get(0),  0.6, 0.0);
        assertEquals(sp.get(1),  2.4, 0.0);
        assertEquals(sp.get(2), -5.0, 0.0);
    }


    /*
    **  - - - - - - -
    **   t _ s x p v
    **  - - - - - - -
    **
    **  Test iauSxpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauSxpv, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_sxpv() {
        Pointer<Pointer<Double>> pv  = Pointer.allocateDoubles(2, 3);
        Pointer<Pointer<Double>> spv = Pointer.allocateDoubles(2, 3);

        double s = 2.0;

        pv.get(0).set(0,  0.3);
        pv.get(0).set(1,  1.2);
        pv.get(0).set(2, -2.5);

        pv.get(1).set(0,  0.5);
        pv.get(1).set(1,  3.2);
        pv.get(1).set(2, -0.7);

        iauSxpv(s, pv.get(), spv.get());

        assertEquals(spv.get(0).get(0),  0.6, 0.0);
        assertEquals(spv.get(0).get(1),  2.4, 0.0);
        assertEquals(spv.get(0).get(2), -5.0, 0.0);

        assertEquals(spv.get(1).get(0),  1.0, 0.0);
        assertEquals(spv.get(1).get(1),  6.4, 0.0);
        assertEquals(spv.get(1).get(2), -1.4, 0.0);
    }

    /*
    **  - - - - - - - -
    **   t _ t a i t t
    **  - - - - - - - -
    **
    **  Test iauTaitt function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauTaitt, assertEquals, assertEquals
    **
    **  This revision:  2010 September 7
    */
    @Test public void t_taitt() {
        Pointer<Double> t1 = Pointer.allocateDouble();
        Pointer<Double> t2 = Pointer.allocateDouble();

        int j = iauTaitt(2453750.5, 0.892482639, t1, t2);

        assertEquals(t1, 2453750.5, 1e-6);
        assertEquals(t2, 0.892855139, 1e-12);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - - - -
    **   t _ t a i u t 1
    **  - - - - - - - - -
    **
    **  Test iauTaiut1 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauTaiut1, assertEquals, assertEquals
    **
    **  This revision:  2010 September 7
    */
    @Test public void t_taiut1() {
        Pointer<Double> u1 = Pointer.allocateDouble();
        Pointer<Double> u2 = Pointer.allocateDouble();

        int j = iauTaiut1(2453750.5, 0.892482639, -32.6659, u1, u2);

        assertEquals(u1, 2453750.5, 1e-6);
        assertEquals(u2, 0.8921045614537037037, 1e-12);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - - - -
    **   t _ t a i u t c
    **  - - - - - - - - - - - -
    **
    **  Test iauTaiutc function.
    **
    **  Returned:
    **     status    LOGICAL     TRUE = success, FALSE = fail
    **
    **  Called:  iauTaiutc, assertEquals, assertEquals
    **
    **  This revision:  2010 September 7
    */
    @Test public void t_taiutc() {
        Pointer<Double> u1 = Pointer.allocateDouble();
        Pointer<Double> u2 = Pointer.allocateDouble();

        int j = iauTaiutc(2453750.5, 0.892482639, u1, u2);

        assertEquals(u1, 2453750.5, 1e-6);
        assertEquals(u2, 0.8921006945555555556, 1e-12);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - - - -
    **   t _ t c b t d b
    **  - - - - - - - - -
    **
    **  Test iauTcbtdb function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauTcbtdb, assertEquals, assertEquals
    **
    **  This revision:  2010 September 6
    */
    @Test public void t_tcbtdb() {
        Pointer<Double> b1 = Pointer.allocateDouble();
        Pointer<Double> b2 = Pointer.allocateDouble();

        int j = iauTcbtdb(2453750.5, 0.893019599, b1, b2);

        assertEquals(b1, 2453750.5, 1e-6);
        assertEquals(b2, 0.8928551362746343397, 1e-12);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - - -
    **   t _ t c g t t
    **  - - - - - - - -
    **
    **  Test iauTcgtt function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauTcgtt, assertEquals, assertEquals
    **
    **  This revision:  2010 September g
    */
    @Test public void t_tcgtt() {
        Pointer<Double> t1 = Pointer.allocateDouble();
        Pointer<Double> t2 = Pointer.allocateDouble();

        int j = iauTcgtt(2453750.5, 0.892862531, t1, t2);

        assertEquals(t1, 2453750.5, 1e-6);
        assertEquals(t2, 0.8928551387488816828, 1e-12);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - - - -
    **   t _ t d b t c b
    **  - - - - - - - - -
    **
    **  Test iauTdbtcb function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauTdbtcb, assertEquals, assertEquals
    **
    **  This revision:  2010 September 6
    */
    @Test public void t_tdbtcb() {
        Pointer<Double> b1 = Pointer.allocateDouble();
        Pointer<Double> b2 = Pointer.allocateDouble();

        int j = iauTdbtcb(2453750.5, 0.892855137, b1, b2);

        assertEquals( b1, 2453750.5, 1e-6);
        assertEquals( b2, 0.8930195997253656716, 1e-12);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - - -
    **   t _ t d b t t
    **  - - - - - - - -
    **
    **  Test iauTdbtt function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauTdbtt, assertEquals, assertEquals
    **
    **  This revision:  2010 September 7
    */
    @Test public void t_tdbtt() {
        Pointer<Double> t1 = Pointer.allocateDouble();
        Pointer<Double> t2 = Pointer.allocateDouble();

        int j = iauTdbtt(2453750.5, 0.892855137, -0.000201, t1, t2);

        assertEquals(t1, 2453750.5, 1e-6);
        assertEquals(t2, 0.8928551393263888889, 1e-12);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - -
    **   t _ t f 2 a
    **  - - - - - - -
    **
    **  Test iauTf2a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauTf2a, assertEquals, assertEquals
    **
    **  This revision:  2010 September 7
    */
    @Test public void t_tf2a() {
        Pointer<Double> a = Pointer.allocateDouble();

        int j = iauTf2a((byte)'+', 4, 58, 20.2, a);
        assertEquals(a, 1.301739278189537429, 1e-12);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - -
    **   t _ t f 2 d
    **  - - - - - - -
    **
    **  Test iauTf2d function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauTf2d, assertEquals, assertEquals
    **
    **  This revision:  2010 September 7
    */
    @Test public void t_tf2d() {
        Pointer<Double> d = Pointer.allocateDouble();

        int j = iauTf2d((byte)' ', 23, 55, 10.9, d);
        assertEquals(d, 0.9966539351851851852, 1e-12);
        assertEquals(j, 0);

    }

    /*
    **  - - - - -
    **   t _ t r
    **  - - - - -
    **
    **  Test iauTr function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauTr, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_tr() {
        Pointer<Pointer<Double>> r  = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> rt = Pointer.allocateDoubles(3, 3);

        r.get(0).set(0, 2.0);
        r.get(0).set(1, 3.0);
        r.get(0).set(2, 2.0);

        r.get(1).set(0, 3.0);
        r.get(1).set(1, 2.0);
        r.get(1).set(2, 3.0);

        r.get(2).set(0, 3.0);
        r.get(2).set(1, 4.0);
        r.get(2).set(2, 5.0);

        iauTr(r.get(), rt.get());

        assertEquals(rt.get(0).get(0), 2.0, 0.0);
        assertEquals(rt.get(0).get(1), 3.0, 0.0);
        assertEquals(rt.get(0).get(2), 3.0, 0.0);

        assertEquals(rt.get(1).get(0), 3.0, 0.0);
        assertEquals(rt.get(1).get(1), 2.0, 0.0);
        assertEquals(rt.get(1).get(2), 4.0, 0.0);

        assertEquals(rt.get(2).get(0), 2.0, 0.0);
        assertEquals(rt.get(2).get(1), 3.0, 0.0);
        assertEquals(rt.get(2).get(2), 5.0, 0.0);
    }

    /*
    **  - - - - - - -
    **   t _ t r x p
    **  - - - - - - -
    **
    **  Test iauTrxp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauTrxp, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_trxp() {
        Pointer<Pointer<Double>> r = Pointer.allocateDoubles(3, 3);
        Pointer<Double> p   = Pointer.allocateDoubles(3);
        Pointer<Double> trp = Pointer.allocateDoubles(3);

        r.get(0).set(0, 2.0);
        r.get(0).set(1, 3.0);
        r.get(0).set(2, 2.0);

        r.get(1).set(0, 3.0);
        r.get(1).set(1, 2.0);
        r.get(1).set(2, 3.0);

        r.get(2).set(0, 3.0);
        r.get(2).set(1, 4.0);
        r.get(2).set(2, 5.0);

        p.set(0, 0.2);
        p.set(1, 1.5);
        p.set(2, 0.1);

        iauTrxp(r.get(), p, trp);

        assertEquals(trp.get(0), 5.2, 1e-12);
        assertEquals(trp.get(1), 4.0, 1e-12);
        assertEquals(trp.get(2), 5.4, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ t r x p v
    **  - - - - - - - -
    **
    **  Test iauTrxpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauTrxpv, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_trxpv() {
        Pointer<Pointer<Double>> r    = Pointer.allocateDoubles(3, 3);
        Pointer<Pointer<Double>> pv   = Pointer.allocateDoubles(2, 3);
        Pointer<Pointer<Double>> trpv = Pointer.allocateDoubles(2, 3);

        r.get(0).set(0, 2.0);
        r.get(0).set(1, 3.0);
        r.get(0).set(2, 2.0);

        r.get(1).set(0, 3.0);
        r.get(1).set(1, 2.0);
        r.get(1).set(2, 3.0);

        r.get(2).set(0, 3.0);
        r.get(2).set(1, 4.0);
        r.get(2).set(2, 5.0);

        pv.get(0).set(0, 0.2);
        pv.get(0).set(1, 1.5);
        pv.get(0).set(2, 0.1);

        pv.get(1).set(0, 1.5);
        pv.get(1).set(1, 0.2);
        pv.get(1).set(2, 0.1);

        iauTrxpv(r.get(), pv.get(), trpv.get());

        assertEquals(trpv.get(0).get(0), 5.2, 1e-12);
        assertEquals(trpv.get(0).get(1), 4.0, 1e-12);
        assertEquals(trpv.get(0).get(2), 5.4, 1e-12);

        assertEquals(trpv.get(1).get(0), 3.9, 1e-12);
        assertEquals(trpv.get(1).get(1), 5.3, 1e-12);
        assertEquals(trpv.get(1).get(2), 4.1, 1e-12);
    }

    /*
    **  - - - - - - - -
    **   t _ t t t a i
    **  - - - - - - - -
    **
    **  Test iauTttai function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauTttai, assertEquals, assertEquals
    **
    **  This revision:  2010 September 7
    */
    @Test public void t_tttai() {
        Pointer<Double> a1 = Pointer.allocateDouble();
        Pointer<Double> a2 = Pointer.allocateDouble();

        int j = iauTttai(2453750.5, 0.892482639, a1, a2);

        assertEquals(a1, 2453750.5, 1e-6);
        assertEquals(a2, 0.892110139, 1e-12);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - - -
    **   t _ t t t c g
    **  - - - - - - - -
    **
    **  Test iauTttcg function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauTttcg, assertEquals, assertEquals
    **
    **  This revision:  2010 September 7
    */
    @Test public void t_tttcg() {
        Pointer<Double> g1 = Pointer.allocateDouble();
        Pointer<Double> g2 = Pointer.allocateDouble();

        int j = iauTttcg(2453750.5, 0.892482639, g1, g2);

        assertEquals( g1, 2453750.5, 1e-6);
        assertEquals( g2, 0.8924900312508587113, 1e-12);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - - -
    **   t _ t t t d b
    **  - - - - - - - -
    **
    **  Test iauTttdb function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauTttdb, assertEquals, assertEquals
    **
    **  This revision:  2010 September 7
    */
    @Test public void t_tttdb() {
        Pointer<Double> b1 = Pointer.allocateDouble();
        Pointer<Double> b2 = Pointer.allocateDouble();

        int j = iauTttdb(2453750.5, 0.892855139, -0.000201, b1, b2);

        assertEquals(b1, 2453750.5, 1e-6);
        assertEquals(b2, 0.8928551366736111111, 1e-12);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - - -
    **   t _ t t u t 1
    **  - - - - - - - -
    **
    **  Test iauTtut1 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauTtut1, assertEquals, assertEquals
    **
    **  This revision:  2010 September 7
    */
    @Test public void t_ttut1() {
        Pointer<Double> u1 = Pointer.allocateDouble();
        Pointer<Double> u2 = Pointer.allocateDouble();

        int j = iauTtut1(2453750.5, 0.892855139, 64.8499, u1, u2);

        assertEquals(u1, 2453750.5, 1e-6);
        assertEquals(u2, 0.8921045614537037037, 1e-12);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - - - -
    **   t _ u t 1 t a i
    **  - - - - - - - - -
    **
    **  Test iauUt1tai function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauUt1tai, assertEquals, assertEquals
    **
    **  This revision:  2010 September 7
    */
    @Test public void t_ut1tai() {
        Pointer<Double> a1 = Pointer.allocateDouble();
        Pointer<Double> a2 = Pointer.allocateDouble();

        int j = iauUt1tai(2453750.5, 0.892104561, -32.6659, a1, a2);

        assertEquals(a1, 2453750.5, 1e-6);
        assertEquals(a2, 0.8924826385462962963, 1e-12);
        //assertEquals(j, 0);
    }

    /*
    **  - - - - - - - - - - -
    **   t _ u t 1 t t
    **  - - - - - - - - - - -
    **
    **  Test iauUt1tt function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauUt1tt, assertEquals, assertEquals
    **
    **  This revision:  2010 September 7
    */
    @Test public void t_ut1tt() {
        Pointer<Double> t1 = Pointer.allocateDouble();
        Pointer<Double> t2 = Pointer.allocateDouble();

        int j = iauUt1tt(2453750.5, 0.892104561, 64.8499, t1, t2);

        assertEquals(t1, 2453750.5, 1e-6);
        assertEquals(t2, 0.8928551385462962963, 1e-12);
        assertEquals(j, 0);

    }

    /*
    **  - - - - - - - - -
    **   t _ u t 1 u t c
    **  - - - - - - - - -
    **
    **  Test iauUt1utc function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauUt1utc, assertEquals, assertEquals
    **
    **  This revision:  2010 September 7
    */
    @Test public void t_ut1utc() {
        Pointer<Double> u1 = Pointer.allocateDouble();
        Pointer<Double> u2 = Pointer.allocateDouble();

        int j = iauUt1utc(2453750.5, 0.892104561, 0.3341, u1, u2);

        assertEquals(u1, 2453750.5, 1e-6);
        assertEquals(u2, 0.8921006941018518519, 1e-12);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - - - -
    **   t _ u t c t a i
    **  - - - - - - - - -
    **
    **  Test iauUtctai function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauUtctai, assertEquals, assertEquals
    **
    **  This revision:  2010 September 7
    */
    @Test public void t_utctai() {
        Pointer<Double> u1 = Pointer.allocateDouble();
        Pointer<Double> u2 = Pointer.allocateDouble();

        int j = iauUtctai(2453750.5, 0.892100694, u1, u2);

        assertEquals(u1, 2453750.5, 1e-6);
        assertEquals(u2, 0.8924826384444444444, 1e-12);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - - - -
    **   t _ u t c u t 1
    **  - - - - - - - - -
    **
    **  Test iauUtcut1 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauUtcut1, assertEquals, assertEquals
    **
    **  This revision:  2010 September 7
    */
    @Test public void t_utcut1() {
        Pointer<Double> u1 = Pointer.allocateDouble();
        Pointer<Double> u2 = Pointer.allocateDouble();

        int j = iauUtcut1(2453750.5, 0.892100694, 0.3341, u1, u2);

        assertEquals(u1, 2453750.5, 1e-6);
        assertEquals(u2, 0.8921045608981481481, 1e-12);
        assertEquals(j, 0);
    }

    /*
    **  - - - - - - -
    **   t _ x y 0 6
    **  - - - - - - -
    **
    **  Test iauXy06 function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauXy06, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_xy06() {
        Pointer<Double> x = Pointer.allocateDouble();
        Pointer<Double> y = Pointer.allocateDouble();

        iauXy06(2400000.5, 53736.0, x, y);

        assertEquals(x, 0.5791308486706010975e-3, 1e-15);
        assertEquals(y, 0.4020579816732958141e-4, 1e-16);
    }

    /*
    **  - - - - - - - - -
    **   t _ x y s 0 0 a
    **  - - - - - - - - -
    **
    **  Test iauXys00a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauXys00a, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_xys00a() {
        Pointer<Double> x = Pointer.allocateDouble();
        Pointer<Double> y = Pointer.allocateDouble();
        Pointer<Double> s = Pointer.allocateDouble();

        iauXys00a(2400000.5, 53736.0, x, y, s);

        assertEquals(x,  0.5791308472168152904e-3, 1e-14);
        assertEquals(y,  0.4020595661591500259e-4, 1e-15);
        assertEquals(s, -0.1220040848471549623e-7, 1e-18);
    }

    /*
    **  - - - - - - - - -
    **   t _ x y s 0 0 b
    **  - - - - - - - - -
    **
    **  Test iauXys00b function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauXys00b, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_xys00b() {
        Pointer<Double> x = Pointer.allocateDouble();
        Pointer<Double> y = Pointer.allocateDouble();
        Pointer<Double> s = Pointer.allocateDouble();

        iauXys00b(2400000.5, 53736.0, x, y, s);

        assertEquals(x,  0.5791301929950208873e-3, 1e-14);
        assertEquals(y,  0.4020553681373720832e-4, 1e-15);
        assertEquals(s, -0.1220027377285083189e-7, 1e-18);
    }

    /*
    **  - - - - - - - - -
    **   t _ x y s 0 6 a
    **  - - - - - - - - -
    **
    **  Test iauXys06a function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauXys06a, assertEquals
    **
    **  This revision:  2008 November 28
    */
    @Test public void t_xys06a() {
        Pointer<Double> x = Pointer.allocateDouble();
        Pointer<Double> y = Pointer.allocateDouble();
        Pointer<Double> s = Pointer.allocateDouble();

        iauXys06a(2400000.5, 53736.0, x, y, s);

        assertEquals(x,  0.5791308482835292617e-3, 1e-14);
        assertEquals(y,  0.4020580099454020310e-4, 1e-15);
        assertEquals(s, -0.1220032294164579896e-7, 1e-18);
    }

    /*
    **  - - - - -
    **   t _ z p
    **  - - - - -
    **
    **  Test iauZp function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauZp, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_zp() {
        Pointer<Double> p = Pointer.allocateDoubles(3);

        p.set(0,  0.3);
        p.set(1,  1.2);
        p.set(2, -2.5);

        iauZp(p);

        assertEquals(p.get(0), 0.0, 0.0);
        assertEquals(p.get(1), 0.0, 0.0);
        assertEquals(p.get(2), 0.0, 0.0);
    }

    /*
    **  - - - - - -
    **   t _ z p v
    **  - - - - - -
    **
    **  Test iauZpv function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauZpv, assertEquals
    **
    **  This revision:  2008 May 25
    */
    @Test public void t_zpv() {
        Pointer<Pointer<Double>> pv = Pointer.allocateDoubles(2, 3);

        pv.get(0).set(0,  0.3);
        pv.get(0).set(1,  1.2);
        pv.get(0).set(2, -2.5);

        pv.get(1).set(0, -0.5);
        pv.get(1).set(1,  3.1);
        pv.get(1).set(2,  0.9);

        iauZpv(pv.get());

        assertEquals(pv.get(0).get(0), 0.0, 0.0);
        assertEquals(pv.get(0).get(1), 0.0, 0.0);
        assertEquals(pv.get(0).get(2), 0.0, 0.0);

        assertEquals(pv.get(1).get(0), 0.0, 0.0);
        assertEquals(pv.get(1).get(1), 0.0, 0.0);
        assertEquals(pv.get(1).get(2), 0.0, 0.0);
    }

    /*
    **  - - - - -
    **   t _ z r
    **  - - - - -
    **
    **  Test iauZr function.
    **
    **  Returned:
    **     status    int         TRUE = success, FALSE = fail
    **
    **  Called:  iauZr, assertEquals
    **
    **  This revision:  2008 November 30
    */
    @Test public void t_zr() {
        Pointer<Pointer<Double>> r = Pointer.allocateDoubles(3, 3);

        r.get(0).set(0, 2.0);
        r.get(1).set(0, 3.0);
        r.get(2).set(0, 2.0);

        r.get(0).set(1, 3.0);
        r.get(1).set(1, 2.0);
        r.get(2).set(1, 3.0);

        r.get(0).set(2, 3.0);
        r.get(1).set(2, 4.0);
        r.get(2).set(2, 5.0);

        iauZr(r.get());

        assertEquals(r.get(0).get(0), 0.0, 0.0);
        assertEquals(r.get(1).get(0), 0.0, 0.0);
        assertEquals(r.get(2).get(0), 0.0, 0.0);

        assertEquals(r.get(0).get(1), 0.0, 0.0);
        assertEquals(r.get(1).get(1), 0.0, 0.0);
        assertEquals(r.get(2).get(1), 0.0, 0.0);

        assertEquals(r.get(0).get(2), 0.0, 0.0);
        assertEquals(r.get(1).get(2), 0.0, 0.0);
        assertEquals(r.get(2).get(2), 0.0, 0.0);
    }

}
