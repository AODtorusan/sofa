package be.angelcorp.sofa;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Array;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
/**
 * <i>native declaration : sofa-platform\src\main\cpp\src\sofam.h:19</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("sofa") 
public class iauASTROM extends StructObject {
	public iauASTROM() {
		super();
	}
	/// PM time interval (SSB, Julian years)
	@Field(0) 
	public double pmt() {
		return this.io.getDoubleField(this, 0);
	}
	/// PM time interval (SSB, Julian years)
	@Field(0) 
	public iauASTROM pmt(double pmt) {
		this.io.setDoubleField(this, 0, pmt);
		return this;
	}
	/**
	 * SSB to observer (vector, au)<br>
	 * C type : double[3]
	 */
	@Array({3}) 
	@Field(1) 
	public Pointer<Double > eb() {
		return this.io.getPointerField(this, 1);
	}
	/**
	 * Sun to observer (unit vector)<br>
	 * C type : double[3]
	 */
	@Array({3}) 
	@Field(2) 
	public Pointer<Double > eh() {
		return this.io.getPointerField(this, 2);
	}
	/// distance from Sun to observer (au)
	@Field(3) 
	public double em() {
		return this.io.getDoubleField(this, 3);
	}
	/// distance from Sun to observer (au)
	@Field(3) 
	public iauASTROM em(double em) {
		this.io.setDoubleField(this, 3, em);
		return this;
	}
	/**
	 * barycentric observer velocity (vector, c)<br>
	 * C type : double[3]
	 */
	@Array({3}) 
	@Field(4) 
	public Pointer<Double > v() {
		return this.io.getPointerField(this, 4);
	}
	/// sqrt(1-|v|^2): reciprocal of Lorenz factor
	@Field(5) 
	public double bm1() {
		return this.io.getDoubleField(this, 5);
	}
	/// sqrt(1-|v|^2): reciprocal of Lorenz factor
	@Field(5) 
	public iauASTROM bm1(double bm1) {
		this.io.setDoubleField(this, 5, bm1);
		return this;
	}
	/**
	 * bias-precession-nutation matrix<br>
	 * C type : double[3][3]
	 */
	@Array({3, 3}) 
	@Field(6) 
	public Pointer<Double > bpn() {
		return this.io.getPointerField(this, 6);
	}
	/// longitude + s' + dERA(DUT) (radians)
	@Field(7) 
	public double along() {
		return this.io.getDoubleField(this, 7);
	}
	/// longitude + s' + dERA(DUT) (radians)
	@Field(7) 
	public iauASTROM along(double along) {
		this.io.setDoubleField(this, 7, along);
		return this;
	}
	/// geodetic latitude (radians)
	@Field(8) 
	public double phi() {
		return this.io.getDoubleField(this, 8);
	}
	/// geodetic latitude (radians)
	@Field(8) 
	public iauASTROM phi(double phi) {
		this.io.setDoubleField(this, 8, phi);
		return this;
	}
	/// polar motion xp wrt local meridian (radians)
	@Field(9) 
	public double xpl() {
		return this.io.getDoubleField(this, 9);
	}
	/// polar motion xp wrt local meridian (radians)
	@Field(9) 
	public iauASTROM xpl(double xpl) {
		this.io.setDoubleField(this, 9, xpl);
		return this;
	}
	/// polar motion yp wrt local meridian (radians)
	@Field(10) 
	public double ypl() {
		return this.io.getDoubleField(this, 10);
	}
	/// polar motion yp wrt local meridian (radians)
	@Field(10) 
	public iauASTROM ypl(double ypl) {
		this.io.setDoubleField(this, 10, ypl);
		return this;
	}
	/// sine of geodetic latitude
	@Field(11) 
	public double sphi() {
		return this.io.getDoubleField(this, 11);
	}
	/// sine of geodetic latitude
	@Field(11) 
	public iauASTROM sphi(double sphi) {
		this.io.setDoubleField(this, 11, sphi);
		return this;
	}
	/// cosine of geodetic latitude
	@Field(12) 
	public double cphi() {
		return this.io.getDoubleField(this, 12);
	}
	/// cosine of geodetic latitude
	@Field(12) 
	public iauASTROM cphi(double cphi) {
		this.io.setDoubleField(this, 12, cphi);
		return this;
	}
	/// magnitude of diurnal aberration vector
	@Field(13) 
	public double diurab() {
		return this.io.getDoubleField(this, 13);
	}
	/// magnitude of diurnal aberration vector
	@Field(13) 
	public iauASTROM diurab(double diurab) {
		this.io.setDoubleField(this, 13, diurab);
		return this;
	}
	/// "local" Earth rotation angle (radians)
	@Field(14) 
	public double eral() {
		return this.io.getDoubleField(this, 14);
	}
	/// "local" Earth rotation angle (radians)
	@Field(14) 
	public iauASTROM eral(double eral) {
		this.io.setDoubleField(this, 14, eral);
		return this;
	}
	/// refraction constant A (radians)
	@Field(15) 
	public double refa() {
		return this.io.getDoubleField(this, 15);
	}
	/// refraction constant A (radians)
	@Field(15) 
	public iauASTROM refa(double refa) {
		this.io.setDoubleField(this, 15, refa);
		return this;
	}
	/// refraction constant B (radians)
	@Field(16) 
	public double refb() {
		return this.io.getDoubleField(this, 16);
	}
	/// refraction constant B (radians)
	@Field(16) 
	public iauASTROM refb(double refb) {
		this.io.setDoubleField(this, 16, refb);
		return this;
	}
	public iauASTROM(Pointer pointer) {
		super(pointer);
	}
}
