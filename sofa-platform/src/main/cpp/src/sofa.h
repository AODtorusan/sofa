#ifndef SOFAHDEF
#define SOFAHDEF

/*
**  - - - - - - -
**   s o f a . h
**  - - - - - - -
**
**  Prototype function declarations for SOFA library.
**
**  This file is part of the International Astronomical Union's
**  SOFA (Standards Of Fundamental Astronomy) software collection.
**
**  This revision:   2013 August 22
**
**  SOFA release 2013-12-02
**
**  Copyright (C) 2013 IAU SOFA Board.  See notes at end.
*/

#include "sofam.h"
#include "math.h"

#ifdef _WIN32
#    define SOFAExport __declspec(dllexport)
#else
#    define SOFAExport
#endif

#ifdef __cplusplus
extern "C" {
#endif

/* Astronomy/Calendars */
SOFAExport int    iauCal2jd(int iy, int im, int id, double *djm0, double *djm);
SOFAExport double iauEpb(double dj1, double dj2);
SOFAExport void   iauEpb2jd(double epb, double *djm0, double *djm);
SOFAExport double iauEpj(double dj1, double dj2);
SOFAExport void   iauEpj2jd(double epj, double *djm0, double *djm);
SOFAExport int    iauJd2cal(double dj1, double dj2, int *iy, int *im, int *id, double *fd);
SOFAExport int    iauJdcalf(int ndp, double dj1, double dj2, int iymdf[4]);

/* Astronomy/Astrometry */
SOFAExport void iauAb(double pnat[3], double v[3], double s, double bm1, double ppr[3]);
SOFAExport void iauApcg(double date1, double date2, double ebpv[2][3], double ehp[3], iauASTROM *astrom);
SOFAExport void iauApcg13(double date1, double date2, iauASTROM *astrom);
SOFAExport void iauApci(double date1, double date2, double ebpv[2][3], double ehp[3], double x, double y, double s, iauASTROM *astrom);
SOFAExport void iauApci13(double date1, double date2, iauASTROM *astrom, double *eo);
SOFAExport void iauApco(double date1, double date2, double ebpv[2][3], double ehp[3], double x, double y, double s, double theta, double elong, double phi, double hm, double xp, double yp, double sp, double refa, double refb, iauASTROM *astrom);
SOFAExport int  iauApco13(double utc1, double utc2, double dut1, double elong, double phi, double hm, double xp, double yp, double phpa, double tk, double rh, double wl, iauASTROM *astrom, double *eo);
SOFAExport void iauApcs(double date1, double date2, double pv[2][3], double ebpv[2][3], double ehp[3], iauASTROM *astrom);
SOFAExport void iauApcs13(double date1, double date2, double pv[2][3], iauASTROM *astrom);
SOFAExport void iauAper(double theta, iauASTROM *astrom);
SOFAExport void iauAper13(double ut11, double ut12, iauASTROM *astrom);
SOFAExport void iauApio(double sp, double theta, double elong, double phi, double hm, double xp, double yp, double refa, double refb, iauASTROM *astrom);
SOFAExport int  iauApio13(double utc1, double utc2, double dut1, double elong, double phi, double hm, double xp, double yp, double phpa, double tk, double rh, double wl, iauASTROM *astrom);
SOFAExport void iauAtci13(double rc, double dc, double pr, double pd, double px, double rv, double date1, double date2, double *ri, double *di, double *eo);
SOFAExport void iauAtciq(double rc, double dc, double pr, double pd, double px, double rv, iauASTROM *astrom, double *ri, double *di);
SOFAExport void iauAtciqn(double rc, double dc, double pr, double pd, double px, double rv, iauASTROM *astrom, int n, iauLDBODY b[], double *ri, double *di);
SOFAExport void iauAtciqz(double rc, double dc, iauASTROM *astrom, double *ri, double *di);
SOFAExport int  iauAtco13(double rc, double dc, double pr, double pd, double px, double rv, double utc1, double utc2, double dut1, double elong, double phi, double hm, double xp, double yp, double phpa, double tk, double rh, double wl, double *aob, double *zob, double *hob, double *dob, double *rob, double *eo);
SOFAExport void iauAtic13(double ri, double di, double date1, double date2, double *rc, double *dc, double *eo);
SOFAExport void iauAticq(double ri, double di, iauASTROM *astrom, double *rc, double *dc);
SOFAExport void iauAticqn(double ri, double di, iauASTROM *astrom,SOFAExport int n, iauLDBODY b[], double *rc, double *dc);
SOFAExport int  iauAtio13(double ri, double di, double utc1, double utc2, double dut1, double elong, double phi, double hm, double xp, double yp, double phpa, double tk, double rh, double wl, double *aob, double *zob, double *hob, double *dob, double *rob);
SOFAExport void iauAtioq(double ri, double di, iauASTROM *astrom, double *aob, double *zob, double *hob, double *dob, double *rob);
SOFAExport int  iauAtoc13(const char *type, double ob1, double ob2, double utc1, double utc2, double dut1, double elong, double phi, double hm, double xp, double yp, double phpa, double tk, double rh, double wl, double *rc, double *dc);
SOFAExport int  iauAtoi13(const char *type, double ob1, double ob2, double utc1, double utc2, double dut1, double elong, double phi, double hm, double xp, double yp, double phpa, double tk, double rh, double wl, double *ri, double *di);
SOFAExport void iauAtoiq(const char *type, double ob1, double ob2, iauASTROM *astrom, double *ri, double *di);
SOFAExport void iauLd(double bm, double p[3], double q[3], double e[3], double em, double dlim, double p1[3]);
SOFAExport void iauLdn(int n, iauLDBODY b[], double ob[3], double sc[3], double sn[3]);
SOFAExport void iauLdsun(double p[3], double e[3], double em, double p1[3]);
SOFAExport void iauPmpx(double rc, double dc, double pr, double pd, double px, double rv, double pmt, double vob[3], double pco[3]);
SOFAExport int  iauPmsafe(double ra1, double dec1, double pmr1, double pmd1, double px1, double rv1, double ep1a, double ep1b, double ep2a, double ep2b, double *ra2, double *dec2, double *pmr2, double *pmd2, double *px2, double *rv2);
SOFAExport void iauPvtob(double elong, double phi, double hm, double xp, double yp, double sp, double theta, double pv[2][3]);
SOFAExport void iauRefco(double phpa, double tk, double rh, double wl, double *refa, double *refb);

/* Astronomy/Ephemerides */
SOFAExport int iauEpv00(double date1, double date2, double pvh[2][3], double pvb[2][3]);
SOFAExport int iauPlan94(double date1, double date2, int np, double pv[2][3]);

/* Astronomy/FundamentalArgs */
SOFAExport double iauFad03(double t);
SOFAExport double iauFae03(double t);
SOFAExport double iauFaf03(double t);
SOFAExport double iauFaju03(double t);
SOFAExport double iauFal03(double t);
SOFAExport double iauFalp03(double t);
SOFAExport double iauFama03(double t);
SOFAExport double iauFame03(double t);
SOFAExport double iauFane03(double t);
SOFAExport double iauFaom03(double t);
SOFAExport double iauFapa03(double t);
SOFAExport double iauFasa03(double t);
SOFAExport double iauFaur03(double t);
SOFAExport double iauFave03(double t);

/* Astronomy/PrecNutPolar */
SOFAExport void iauBi00(double *dpsibi, double *depsbi, double *dra);
SOFAExport void iauBp00(double date1, double date2, double rb[3][3], double rp[3][3], double rbp[3][3]);
SOFAExport void iauBp06(double date1, double date2, double rb[3][3], double rp[3][3], double rbp[3][3]);
SOFAExport void iauBpn2xy(double rbpn[3][3], double *x, double *y);
SOFAExport void iauC2i00a(double date1, double date2, double rc2i[3][3]);
SOFAExport void iauC2i00b(double date1, double date2, double rc2i[3][3]);
SOFAExport void iauC2i06a(double date1, double date2, double rc2i[3][3]);
SOFAExport void iauC2ibpn(double date1, double date2, double rbpn[3][3], double rc2i[3][3]);
SOFAExport void iauC2ixy(double date1, double date2, double x, double y, double rc2i[3][3]);
SOFAExport void iauC2ixys(double x, double y, double s, double rc2i[3][3]);
SOFAExport void iauC2t00a(double tta, double ttb, double uta, double utb, double xp, double yp, double rc2t[3][3]);
SOFAExport void iauC2t00b(double tta, double ttb, double uta, double utb, double xp, double yp, double rc2t[3][3]);
SOFAExport void iauC2t06a(double tta, double ttb, double uta, double utb, double xp, double yp, double rc2t[3][3]);
SOFAExport void iauC2tcio(double rc2i[3][3], double era, double rpom[3][3], double rc2t[3][3]);
SOFAExport void iauC2teqx(double rbpn[3][3], double gst, double rpom[3][3], double rc2t[3][3]);
SOFAExport void iauC2tpe(double tta, double ttb, double uta, double utb, double dpsi, double deps, double xp, double yp, double rc2t[3][3]);
SOFAExport void iauC2txy(double tta, double ttb, double uta, double utb, double x, double y, double xp, double yp, double rc2t[3][3]);
SOFAExport double iauEo06a(double date1, double date2);
SOFAExport double iauEors(double rnpb[3][3], double s);
SOFAExport void iauFw2m(double gamb, double phib, double psi, double eps, double r[3][3]);
SOFAExport void iauFw2xy(double gamb, double phib, double psi, double eps, double *x, double *y);
SOFAExport void iauNum00a(double date1, double date2, double rmatn[3][3]);
SOFAExport void iauNum00b(double date1, double date2, double rmatn[3][3]);
SOFAExport void iauNum06a(double date1, double date2, double rmatn[3][3]);
SOFAExport void iauNumat(double epsa, double dpsi, double deps, double rmatn[3][3]);
SOFAExport void iauNut00a(double date1, double date2, double *dpsi, double *deps);
SOFAExport void iauNut00b(double date1, double date2, double *dpsi, double *deps);
SOFAExport void iauNut06a(double date1, double date2, double *dpsi, double *deps);
SOFAExport void iauNut80(double date1, double date2, double *dpsi, double *deps);
SOFAExport void iauNutm80(double date1, double date2, double rmatn[3][3]);
SOFAExport double iauObl06(double date1, double date2);
SOFAExport double iauObl80(double date1, double date2);
SOFAExport void iauP06e(double date1, double date2, double *eps0, double *psia, double *oma, double *bpa, double *bqa, double *pia, double *bpia, double *epsa, double *chia, double *za, double *zetaa, double *thetaa, double *pa, double *gam, double *phi, double *psi);
SOFAExport void iauPb06(double date1, double date2, double *bzeta, double *bz, double *btheta);
SOFAExport void iauPfw06(double date1, double date2, double *gamb, double *phib, double *psib, double *epsa);
SOFAExport void iauPmat00(double date1, double date2, double rbp[3][3]);
SOFAExport void iauPmat06(double date1, double date2, double rbp[3][3]);
SOFAExport void iauPmat76(double date1, double date2, double rmatp[3][3]);
SOFAExport void iauPn00(double date1, double date2, double dpsi, double deps, double *epsa, double rb[3][3], double rp[3][3], double rbp[3][3], double rn[3][3], double rbpn[3][3]);
SOFAExport void iauPn00a(double date1, double date2, double *dpsi, double *deps, double *epsa, double rb[3][3], double rp[3][3], double rbp[3][3], double rn[3][3], double rbpn[3][3]);
SOFAExport void iauPn00b(double date1, double date2, double *dpsi, double *deps, double *epsa, double rb[3][3], double rp[3][3], double rbp[3][3], double rn[3][3], double rbpn[3][3]);
SOFAExport void iauPn06(double date1, double date2, double dpsi, double deps, double *epsa, double rb[3][3], double rp[3][3], double rbp[3][3], double rn[3][3], double rbpn[3][3]);
SOFAExport void iauPn06a(double date1, double date2, double *dpsi, double *deps, double *epsa, double rb[3][3], double rp[3][3], double rbp[3][3], double rn[3][3], double rbpn[3][3]);
SOFAExport void iauPnm00a(double date1, double date2, double rbpn[3][3]);
SOFAExport void iauPnm00b(double date1, double date2, double rbpn[3][3]);
SOFAExport void iauPnm06a(double date1, double date2, double rnpb[3][3]);
SOFAExport void iauPnm80(double date1, double date2, double rmatpn[3][3]);
SOFAExport void iauPom00(double xp, double yp, double sp, double rpom[3][3]);
SOFAExport void iauPr00(double date1, double date2, double *dpsipr, double *depspr);
SOFAExport void iauPrec76(double ep01, double ep02, double ep11, double ep12, double *zeta, double *z, double *theta);
SOFAExport double iauS00(double date1, double date2, double x, double y);
SOFAExport double iauS00a(double date1, double date2);
SOFAExport double iauS00b(double date1, double date2);
SOFAExport double iauS06(double date1, double date2, double x, double y);
SOFAExport double iauS06a(double date1, double date2);
SOFAExport double iauSp00(double date1, double date2);
SOFAExport void iauXy06(double date1, double date2, double *x, double *y);
SOFAExport void iauXys00a(double date1, double date2, double *x, double *y, double *s);
SOFAExport void iauXys00b(double date1, double date2, double *x, double *y, double *s);
SOFAExport void iauXys06a(double date1, double date2, double *x, double *y, double *s);

/* Astronomy/RotationAndTime */
SOFAExport double iauEe00(double date1, double date2, double epsa, double dpsi);
SOFAExport double iauEe00a(double date1, double date2);
SOFAExport double iauEe00b(double date1, double date2);
SOFAExport double iauEe06a(double date1, double date2);
SOFAExport double iauEect00(double date1, double date2);
SOFAExport double iauEqeq94(double date1, double date2);
SOFAExport double iauEra00(double dj1, double dj2);
SOFAExport double iauGmst00(double uta, double utb, double tta, double ttb);
SOFAExport double iauGmst06(double uta, double utb, double tta, double ttb);
SOFAExport double iauGmst82(double dj1, double dj2);
SOFAExport double iauGst00a(double uta, double utb, double tta, double ttb);
SOFAExport double iauGst00b(double uta, double utb);
SOFAExport double iauGst06(double uta, double utb, double tta, double ttb, double rnpb[3][3]);
SOFAExport double iauGst06a(double uta, double utb, double tta, double ttb);
SOFAExport double iauGst94(double uta, double utb);

/* Astronomy/SpaceMotion */
SOFAExport int iauPmsafe(double ra1, double dec1, double pmr1, double pmd1, double px1, double rv1, double ep1a, double ep1b, double ep2a, double ep2b, double *ra2, double *dec2, double *pmr2, double *pmd2, double *px2, double *rv2);
SOFAExport int iauPvstar(double pv[2][3], double *ra, double *dec, double *pmr, double *pmd, double *px, double *rv);
SOFAExport int iauStarpv(double ra, double dec, double pmr, double pmd, double px, double rv, double pv[2][3]);

/* Astronomy/StarCatalogs */
SOFAExport void iauFk52h(double r5, double d5, double dr5, double dd5, double px5, double rv5, double *rh, double *dh, double *drh, double *ddh, double *pxh, double *rvh);
SOFAExport void iauFk5hip(double r5h[3][3], double s5h[3]);
SOFAExport void iauFk5hz(double r5, double d5, double date1, double date2, double *rh, double *dh);
SOFAExport void iauH2fk5(double rh, double dh, double drh, double ddh, double pxh, double rvh, double *r5, double *d5, double *dr5, double *dd5, double *px5, double *rv5);
SOFAExport void iauHfk5z(double rh, double dh, double date1, double date2, double *r5, double *d5, double *dr5, double *dd5);
SOFAExport int  iauStarpm(double ra1, double dec1, double pmr1, double pmd1, double px1, double rv1, double ep1a, double ep1b, double ep2a, double ep2b, double *ra2, double *dec2, double *pmr2, double *pmd2, double *px2, double *rv2);

/* Astronomy/GeodeticGeocentric */
SOFAExport int  iauEform(int n, double *a, double *f);
SOFAExport int  iauGc2gd(int n, double xyz[3], double *elong, double *phi, double *height);
SOFAExport int  iauGc2gde(double a, double f, double xyz[3], double *elong, double *phi, double *height);
SOFAExport int  iauGd2gc(int n, double elong, double phi, double height, double xyz[3]);
SOFAExport int  iauGd2gce(double a, double f, double elong, double phi, double height, double xyz[3]);
SOFAExport void iauPvtob(double elong, double phi, double height, double xp, double yp, double sp, double theta, double pv[2][3]);

/* Astronomy/Timescales */
SOFAExport int iauD2dtf(const char *scale, int ndp, double d1, double d2, int *iy, int *im, int *id, int ihmsf[4]);
SOFAExport int iauDat(int iy, int im, int id, double fd, double *deltat);
SOFAExport double iauDtdb(double date1, double date2, double ut, double elong, double u, double v);
SOFAExport int iauDtf2d(const char *scale, int iy, int im, int id, int ihr, int imn, double sec, double *d1, double *d2);
SOFAExport int iauTaitt(double tai1, double tai2, double *tt1, double *tt2);
SOFAExport int iauTaiut1(double tai1, double tai2, double dta, double *ut11, double *ut12);
SOFAExport int iauTaiutc(double tai1, double tai2, double *utc1, double *utc2);
SOFAExport int iauTcbtdb(double tcb1, double tcb2, double *tdb1, double *tdb2);
SOFAExport int iauTcgtt(double tcg1, double tcg2, double *tt1, double *tt2);
SOFAExport int iauTdbtcb(double tdb1, double tdb2, double *tcb1, double *tcb2);
SOFAExport int iauTdbtt(double tdb1, double tdb2, double dtr, double *tt1, double *tt2);
SOFAExport int iauTttai(double tt1, double tt2, double *tai1, double *tai2);
SOFAExport int iauTttcg(double tt1, double tt2, double *tcg1, double *tcg2);
SOFAExport int iauTttdb(double tt1, double tt2, double dtr, double *tdb1, double *tdb2);
SOFAExport int iauTtut1(double tt1, double tt2, double dt, double *ut11, double *ut12);
SOFAExport int iauUt1tai(double ut11, double ut12, double dta, double *tai1, double *tai2);
SOFAExport int iauUt1tt(double ut11, double ut12, double dt, double *tt1, double *tt2);
SOFAExport int iauUt1utc(double ut11, double ut12, double dut1, double *utc1, double *utc2);
SOFAExport int iauUtctai(double utc1, double utc2, double *tai1, double *tai2);
SOFAExport int iauUtcut1(double utc1, double utc2, double dut1, double *ut11, double *ut12);

/* VectorMatrix/AngleOps */
SOFAExport void iauA2af(int ndp, double angle, char *sign, int idmsf[4]);
SOFAExport void iauA2tf(int ndp, double angle, char *sign, int ihmsf[4]);
SOFAExport int iauAf2a(char s, int ideg, int iamin, double asec, double *rad);
SOFAExport double iauAnp(double a);
SOFAExport double iauAnpm(double a);
SOFAExport void iauD2tf(int ndp, double days, char *sign, int ihmsf[4]);
SOFAExport int iauTf2a(char s, int ihour, int imin, double sec, double *rad);
SOFAExport int iauTf2d(char s, int ihour, int imin, double sec, double *days);

/* VectorMatrix/BuildRotations */
SOFAExport void iauRx(double phi, double r[3][3]);
SOFAExport void iauRy(double theta, double r[3][3]);
SOFAExport void iauRz(double psi, double r[3][3]);

/* VectorMatrix/CopyExtendExtract */
SOFAExport void iauCp(double p[3], double c[3]);
SOFAExport void iauCpv(double pv[2][3], double c[2][3]);
SOFAExport void iauCr(double r[3][3], double c[3][3]);
SOFAExport void iauP2pv(double p[3], double pv[2][3]);
SOFAExport void iauPv2p(double pv[2][3], double p[3]);

/* VectorMatrix/Initialization */
SOFAExport void iauIr(double r[3][3]);
SOFAExport void iauZp(double p[3]);
SOFAExport void iauZpv(double pv[2][3]);
SOFAExport void iauZr(double r[3][3]);

/* VectorMatrix/MatrixOps */
SOFAExport void iauRxr(double a[3][3], double b[3][3], double atb[3][3]);
SOFAExport void iauTr(double r[3][3], double rt[3][3]);

/* VectorMatrix/MatrixVectorProducts */
SOFAExport void iauRxp(double r[3][3], double p[3], double rp[3]);
SOFAExport void iauRxpv(double r[3][3], double pv[2][3], double rpv[2][3]);
SOFAExport void iauTrxp(double r[3][3], double p[3], double trp[3]);
SOFAExport void iauTrxpv(double r[3][3], double pv[2][3], double trpv[2][3]);

/* VectorMatrix/RotationVectors */
SOFAExport void iauRm2v(double r[3][3], double w[3]);
SOFAExport void iauRv2m(double w[3], double r[3][3]);

/* VectorMatrix/SeparationAndAngle */
SOFAExport double iauPap(double a[3], double b[3]);
SOFAExport double iauPas(double al, double ap, double bl, double bp);
SOFAExport double iauSepp(double a[3], double b[3]);
SOFAExport double iauSeps(double al, double ap, double bl, double bp);

/* VectorMatrix/SphericalCartesian */
SOFAExport void iauC2s(double p[3], double *theta, double *phi);
SOFAExport void iauP2s(double p[3], double *theta, double *phi, double *r);
SOFAExport void iauPv2s(double pv[2][3], double *theta, double *phi, double *r, double *td, double *pd, double *rd);
SOFAExport void iauS2c(double theta, double phi, double c[3]);
SOFAExport void iauS2p(double theta, double phi, double r, double p[3]);
SOFAExport void iauS2pv(double theta, double phi, double r, double td, double pd, double rd, double pv[2][3]);

/* VectorMatrix/VectorOps */
SOFAExport double iauPdp(double a[3], double b[3]);
SOFAExport double iauPm(double p[3]);
SOFAExport void iauPmp(double a[3], double b[3], double amb[3]);
SOFAExport void iauPn(double p[3], double *r, double u[3]);
SOFAExport void iauPpp(double a[3], double b[3], double apb[3]);
SOFAExport void iauPpsp(double a[3], double s, double b[3], double apsb[3]);
SOFAExport void iauPvdpv(double a[2][3], double b[2][3], double adb[2]);
SOFAExport void iauPvm(double pv[2][3], double *r, double *s);
SOFAExport void iauPvmpv(double a[2][3], double b[2][3], double amb[2][3]);
SOFAExport void iauPvppv(double a[2][3], double b[2][3], double apb[2][3]);
SOFAExport void iauPvu(double dt, double pv[2][3], double upv[2][3]);
SOFAExport void iauPvup(double dt, double pv[2][3], double p[3]);
SOFAExport void iauPvxpv(double a[2][3], double b[2][3], double axb[2][3]);
SOFAExport void iauPxp(double a[3], double b[3], double axb[3]);
SOFAExport void iauS2xpv(double s1, double s2, double pv[2][3], double spv[2][3]);
SOFAExport void iauSxp(double s, double p[3], double sp[3]);
SOFAExport void iauSxpv(double s, double pv[2][3], double spv[2][3]);

#ifdef __cplusplus
}
#endif

#endif

/*----------------------------------------------------------------------
**
**  Copyright (C) 2013
**  Standards Of Fundamental Astronomy Board
**  of the International Astronomical Union.
**
**  =====================
**  SOFA Software License
**  =====================
**
**  NOTICE TO USER:
**
**  BY USING THIS SOFTWARE YOU ACCEPT THE FOLLOWING SIX TERMS AND
**  CONDITIONS WHICH APPLY TO ITS USE.
**
**  1. The Software is owned by the IAU SOFA Board ("SOFA").
**
**  2. Permission is granted to anyone to use the SOFA software for any
**     purpose, including commercial applications, free of charge and
**     without payment of royalties, subject to the conditions and
**     restrictions listed below.
**
**  3. You (the user) may copy and distribute SOFA source code to others,
**     and use and adapt its code and algorithms in your own software,
**     on a world-wide, royalty-free basis.  That portion of your
**     distribution that does not consist of intact and unchanged copies
**     of SOFA source code files is a "derived work" that must comply
**     with the following requirements:
**
**     a) Your work shall be marked or carry a statement that it
**        (i) uses routines and computations derived by you from
**        software provided by SOFA under license to you; and
**        (ii) does not itself constitute software provided by and/or
**        endorsed by SOFA.
**
**     b) The source code of your derived work must contain descriptions
**        of how the derived work is based upon, contains and/or differs
**        from the original SOFA software.
**
**     c) The names of all routines in your derived work shall not
**        include the prefix "iau" or "sofa" or trivial modifications
**        thereof such as changes of case.
**
**     d) The origin of the SOFA components of your derived work must
**        not be misrepresented;  you must not claim that you wrote the
**        original software, nor file a patent application for SOFA
**        software or algorithms embedded in the SOFA software.
**
**     e) These requirements must be reproduced intact in any source
**        distribution and shall apply to anyone to whom you have
**        granted a further right to modify the source code of your
**        derived work.
**
**     Note that, as originally distributed, the SOFA software is
**     intended to be a definitive implementation of the IAU standards,
**     and consequently third-party modifications are discouraged.  All
**     variations, no matter how minor, must be explicitly marked as
**     such, as explained above.
**
**  4. You shall not cause the SOFA software to be brought into
**     disrepute, either by misuse, or use for inappropriate tasks, or
**     by inappropriate modification.
**
**  5. The SOFA software is provided "as is" and SOFA makes no warranty
**     as to its use or performance.   SOFA does not and cannot warrant
**     the performance or results which the user may obtain by using the
**     SOFA software.  SOFA makes no warranties, express or implied, as
**     to non-infringement of third party rights, merchantability, or
**     fitness for any particular purpose.  In no event will SOFA be
**     liable to the user for any consequential, incidental, or special
**     damages, including any lost profits or lost savings, even if a
**     SOFA representative has been advised of such damages, or for any
**     claim by any third party.
**
**  6. The provision of any version of the SOFA software under the terms
**     and conditions specified herein does not imply that future
**     versions will also be made available under the same terms and
**     conditions.
*
**  In any published work or commercial product which uses the SOFA
**  software directly, acknowledgement (see www.iausofa.org) is
**  appreciated.
**
**  Correspondence concerning SOFA software should be addressed as
**  follows:
**
**      By email:  sofa@ukho.gov.uk
**      By post:   IAU SOFA Center
**                 HM Nautical Almanac Office
**                 UK Hydrographic Office
**                 Admiralty Way, Taunton
**                 Somerset, TA1 2DN
**                 United Kingdom
**
**--------------------------------------------------------------------*/
