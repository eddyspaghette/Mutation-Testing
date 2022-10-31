package mikera.matrixx.algo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mikera.matrixx.AMatrix;
import mikera.matrixx.Matrix;
import mikera.matrixx.Matrixx;


public class TestDenseMultiply {

	@Test
	public void testBigMul() {
		Matrix m=(Matrix)Matrixx.createRandomMatrix(50, 50);
		AMatrix ip=m.innerProduct(m);
		assertEquals(ip,Multiplications.multiply(m, m));
		assertEquals(ip,Multiplications.blockedMultiply(m, m));
		assertEquals(ip,Multiplications.doubleBlockedMultiply(m, m));
		assertEquals(ip,Multiplications.naiveMultiply(m, m));
	}
	
	@Test public void testSmallMul() {
		Matrix m=(Matrix)Matrixx.createRandomMatrix(5, 5);
		AMatrix ip=m.innerProduct(m);
		assertEquals(ip,Multiplications.multiply(m, m));
		assertEquals(ip,Multiplications.blockedMultiply(m, m));
		assertEquals(ip,Multiplications.naiveMultiply(m, m));
	}
	
	@Test public void testRectangularMul() {
		Matrix m=(Matrix)Matrixx.createRandomMatrix(4, 7);
		Matrix mt=m.toMatrixTranspose();
		AMatrix ip=m.innerProduct(mt);
		assertEquals(ip,Multiplications.multiply(m, mt));
		assertEquals(ip,Multiplications.blockedMultiply(m, mt));
		assertEquals(ip,Multiplications.naiveMultiply(m, mt));
		assertEquals(ip,Multiplications.directMultiply(m, mt));
	}
	
	@Test public void testTransposeMul() {
		Matrix m=(Matrix)Matrixx.createRandomMatrix(6, 3);
		Matrix mt=m.toMatrixTranspose();
		Matrix ip=m.transposeInnerProduct(m);
		assertEquals(ip,Multiplications.multiply(mt, m));
		assertEquals(ip,Multiplications.blockedMultiply(mt, m));
		assertEquals(ip,Multiplications.naiveMultiply(mt, m));
	}
}
