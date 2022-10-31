package mikera.matrixx.algo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import mikera.matrixx.Matrix;
import mikera.matrixx.Matrixx;

public class TestDeterminant {

	@SuppressWarnings("unused")
	@Test
	public void testDeterminant() {
		Matrix m=Matrixx.createRandomSquareMatrix(8);
		double d=m.determinant();
	}
	
	@Test
	public void testDetEquivalence3() {
		Matrix m=Matrixx.createRandomSquareMatrix(3);
		assertEquals(Determinant.naiveDeterminant(m),Determinant.calculateLUPDeterminant(m),0.0001);
	}
	
	@Test
	public void testDetEquivalence4() {
		Matrix m=Matrixx.createRandomSquareMatrix(4);
		assertEquals(Determinant.naiveDeterminant(m),Determinant.calculateLUPDeterminant(m),0.0001);
	}

}
