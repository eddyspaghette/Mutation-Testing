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

import mikera.matrixx.AMatrix;
import mikera.matrixx.decompose.IQRResult;

/**
 * <p>
 * QR decompositions decompose a rectangular matrix 'A' such that 'A=QR'.  Where
 * A &isin; &real; <sup>n &times; m</sup> , n &ge; m, Q &isin; &real; <sup>n &times; n</sup> is an orthogonal matrix,
 * and R &isin; &real; <sup>n &times; m</sup> is an upper triangular matrix.  Some implementations
 * of QR decomposition require that A has full rank.
 * </p>
 * <p>
 * Some features of QR decompositions:
 * <ul>
 * <li> Can decompose rectangular matrices. </li>
 * <li> Numerically stable solutions to least-squares problem, but not as stable as SVD </li>
 * <li> Can incrementally add and remove columns from the decomposed matrix.  See {@link org.ejml.alg.dense.linsol.qr.AdjLinearSolverQr} </li>
 * </ul>
 * </p>
 * <p>
 * Orthogonal matrices have the following properties:
 * <ul>
 * <li>QQ<sup>T</sup>=I</li>
 * <li>Q<sup>T</sup>=Q<sup>-1</sup></li>
 * </ul>
 * </p>
 *
 * @author Peter Abeles
 */
public interface QRDecomposition {
    public IQRResult decompose( AMatrix A );
}
