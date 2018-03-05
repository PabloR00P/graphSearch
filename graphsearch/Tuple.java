/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphsearch;

public class Tuple<A, B> {
    public final A x;
    public final B y;

    public Tuple(final A x, final B y) {
        this.x = x;
        this.y = y;
    }

    public B getY() {
        return y;
    }

    public A getX() {
        return x;
    }
}
