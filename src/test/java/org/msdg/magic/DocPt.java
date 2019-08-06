package org.msdg.magic;

/**
 * Created by msdg on 2019-08-06 19:33.
 * Look, there is a moon.
 */
public class DocPt {

    private String next_cid;
    private String next_url;
    private String pre_cid;
    private String pre_url;

    public String getNext_cid() {
        return next_cid;
    }

    public void setNext_cid(String next_cid) {
        this.next_cid = next_cid;
    }

    public String getNext_url() {
        return next_url;
    }

    public void setNext_url(String next_url) {
        this.next_url = next_url;
    }

    public String getPre_cid() {
        return pre_cid;
    }

    public void setPre_cid(String pre_cid) {
        this.pre_cid = pre_cid;
    }

    public String getPre_url() {
        return pre_url;
    }

    public void setPre_url(String pre_url) {
        this.pre_url = pre_url;
    }

    @Override
    public String toString() {
        return "DocPt{" +
                "next_cid='" + next_cid + '\'' +
                ", next_url='" + next_url + '\'' +
                ", pre_cid='" + pre_cid + '\'' +
                ", pre_url='" + pre_url + '\'' +
                '}';
    }
}
