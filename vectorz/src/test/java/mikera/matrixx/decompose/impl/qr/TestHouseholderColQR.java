/*
 * Copyright (c) 2009-2013, Peter Abeles. All Rights Reserved.
 *
 * This file is part of Efficient Java Matrix Library (EJML).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mikera.matrixx.decompose.impl.qr;

import java.util.Random;

import mikera.matrixx.Matrix;
import mikera.matrixx.algo.Multiplications;
import mikera.matrixx.impl.AStridedMatrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Peter Abeles
 */
public class TestHouseholderColQR extends GenericQrCheck {

    Random rand = new Random(0xff);
    
    @Override
    protected QRDecomposition createQRDecomposition(boolean compact)
    {
        return new HouseholderColQR(compact);
    }

    /**
     * Internal several householder operations are performed.  This
     * checks to see if the householder operations and the expected result for all the
     * submatrices.
     */
    @Test
    public void householder() {
        int width = 5;

        for( int i = 0; i < width; i++ ) {
            checkSubHouse(i , width);
        }
    }

    private void checkSubHouse(int w , int width) {
        DebugQR qr = new DebugQR(width,width);

        Matrix A = Matrix.createRandom(width, width);

        qr.householder(w,A);

//        SimpleMatrix U = new SimpleMatrix(width,1, true, qr.getQR()[w]).extractMatrix(w,width,0,1);
        Matrix temp = Matrix.create(width,1);
        temp.setElements(qr.getQR()[w]);
        AStridedMatrix U = temp.subMatrix(w, width-w, 0, 1);
        
        U.set(0,0,1); // this is not explicity set and is assumed to be 1
        Matrix I = Matrix.createIdentity(width-w);
//        SimpleMatrix Q = I.minus(U.mult(U.transpose()).scale(qr.getGamma()));
        Matrix temp1 = Multiplications.multiply(U, U.getTranspose());
        temp1.scale(qr.getGamma());
        I.sub(temp1);
        Matrix Q = I;


        // check the expected properties of Q
        assertTrue(Q.epsilonEquals(Q.getTranspose(),1e-6));
        assertTrue(Q.epsilonEquals(Q.inverse(),1e-6));

//        SimpleMatrix result = Q.mult(A.extractMatrix(w,width,w,width));
        AStridedMatrix result = Multiplications.multiply(Q, A.subMatrix(w,width-w,w,width-w));

        for( int i = 1; i < width-w; i++ ) {
            assertEquals(0,result.get(i,0),1e-5);
        }
    }

    /**
     * Check the results of this function against basic matrix operations
     * which are equivalent.
     */
    @Test
    public void updateA() {
        int width = 5;

        for( int i = 0; i < width; i++ )
            checkSubMatrix(width,i);
    }

    private void checkSubMatrix(int width , int w ) {
        DebugQR qr = new DebugQR(width,width);

        double gamma = 0.2;
        double tau = 0.75;

        Matrix U = Matrix.createRandom(width, 1);
        Matrix A = Matrix.createRandom(width, width);

        qr.convertToColumnMajor(A);

        // compute the results using standard matrix operations
        Matrix I = Matrix.createIdentity(width-w);

//        SimpleMatrix u_sub = U.extractMatrix(w,width,0,1);
        AStridedMatrix u_sub = U.subMatrix(w, width-w, 0, 1);
        u_sub.set(0,0,1);// assumed to be 1 in the algorithm
//        SimpleMatrix A_sub = A.extractMatrix(w,width,w,width);
        AStridedMatrix A_sub = A.subMatrix(w,width-w,w,width-w);
//        SimpleMatrix expected = I.minus(u_sub.mult(u_sub.transpose()).scale(gamma)).mult(A_sub);
        Matrix temp1 = Multiplications.multiply(u_sub, u_sub.getTranspose());
        temp1.scale(gamma);
        I.sub(temp1);
        Matrix expected = Multiplications.multiply(I, A_sub);

        qr.updateA(w,U.asDoubleArray(),gamma,tau);

        double[][] found = qr.getQR();

        for( int i = w+1; i < width; i++ ) {
            assertEquals(U.get(i,0),found[w][i],1e-8);
        }

        // the right should be the same
        for( int i = w; i < width; i++ ) {
            for( int j = w+1; j < width; j++ ) {
                double a = expected.get(i-w,j-w);
                double b = found[j][i];

                assertEquals(a,b,1e-6);
            }
        }
    }

    private static class DebugQR extends HouseholderColQR
    {

        public DebugQR( int numRows , int numCols ) {
            super(false);
            error = false;

            this.numCols = numCols;
            this.numRows = numRows;
            minLength = Math.min(numRows,numCols);
            int maxLength = Math.max(numRows,numCols);

            dataQR = new double[ numCols ][  numRows ];
            v = new double[ maxLength ];
            gammas = new double[ minLength ];
            
            this.numCols = numCols;
            this.numRows = numRows;
        }

        public void householder( int j , Matrix A ) {
            convertToColumnMajor(A);

            super.householder(j);
        }

        protected void convertToColumnMajor(Matrix A) {
            super.convertToColumnMajor(A);
        }

        public void updateA( int w , double u[] , double gamma , double tau ) {
            System.arraycopy(u,0,this.dataQR[w],0,u.length);
            this.gamma = gamma;
            this.tau = tau;

            super.updateA(w);
        }

        public double getGamma() {
            return gamma;
        }
    }
}