package mikera.vectorz;

import mikera.matrixx.Matrix33;
import mikera.matrixx.Matrixx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestQuaternions {
	@Test public void testInvert() {
		assertTrue(Vector4.of(0,0,0,1).epsilonEquals(Quaternions.invert(Vector4.of(0,0,0,1)),0.0));
		
		Vector4 a=Vector4.of(1,2,3,4);
		Vector4 b=Quaternions.invert(a);
		
		Vector4 r1=Quaternions.mul(a,b);
		// assertEquals(Vector4.of(0,0,0,1),r1);
		assertTrue(r1.epsilonEquals(Vector4.of(0,0,0,1)));
		
		Vector4 r2=Quaternions.mul(b,a);
		// assertEquals(Vector4.of(0,0,0,1),r2);
		assertTrue(r2.epsilonEquals(Vector4.of(0,0,0,1)));
	}

	@Test public void testMulWithBoundaryVals() {
	    Vector4 a=Vector4.of(Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE);
	    Vector4 b = new Vector4(Double.MAX_VALUE,0,Double.MAX_VALUE,0);
	    Vector4 q=Quaternions.mul(a,b);
	    Vector4 expected=Vector4.of(Double.POSITIVE_INFINITY,Double.NaN,Double.NaN,Double.NEGATIVE_INFINITY);
	    assertTrue(q.epsilonEquals(expected));
	}

	
	@Test public void testRotate() {
		Vector3 axis=new Vector3(1,0,0);
		Vector3 v=new Vector3(0,1,0);
		double angle=0.5*Math.PI;
		
		Matrix33 rotM = Matrixx.createRotationMatrix(axis, angle);
		Vector4 rotQ = Quaternions.axisAngle(axis, angle);
		
		Vector3 mv=rotM.innerProduct(v);
		Vector3 qv=Quaternions.rotate(rotQ, v);
		
		assertEquals(mv,qv);
		assertTrue(mv.epsilonEquals(qv));
	}
}
