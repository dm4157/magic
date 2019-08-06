package org.msdg.magic;

/**
 * Created by msdg on 2019-08-06 19:31.
 * Look, there is a moon.
 */
public class Doc {

    private int errno;
    private DocData data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public DocData getData() {
        return data;
    }

    public void setData(DocData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Doc{" +
                "errno=" + errno +
                ", data=" + data +
                '}';
    }
}
