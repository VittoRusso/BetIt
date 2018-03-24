
package co.edu.uninorte.betit.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Groups {

    @SerializedName("a")
    @Expose
    private A a;
    @SerializedName("b")
    @Expose
    private B b;
    @SerializedName("c")
    @Expose
    private C c;
    @SerializedName("d")
    @Expose
    private D d;
    @SerializedName("e")
    @Expose
    private E e;
    @SerializedName("f")
    @Expose
    private F f;
    @SerializedName("g")
    @Expose
    private G g;
    @SerializedName("h")
    @Expose
    private H h;

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }

    public D getD() {
        return d;
    }

    public void setD(D d) {
        this.d = d;
    }

    public E getE() {
        return e;
    }

    public void setE(E e) {
        this.e = e;
    }

    public F getF() {
        return f;
    }

    public void setF(F f) {
        this.f = f;
    }

    public G getG() {
        return g;
    }

    public void setG(G g) {
        this.g = g;
    }

    public H getH() {
        return h;
    }

    public void setH(H h) {
        this.h = h;
    }

}
