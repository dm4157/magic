package org.msdg.magic;

/**
 * Created by msdg on 2019-08-06 19:32.
 * Look, there is a moon.
 */
public class DocData {

    private String chapter_index;

    private String cid;
    private String content;
    private boolean hasCache;
    private boolean isLogin;
    private boolean readAuthority;
    private String site;
    private String title;
    private String url;

    private DocPt pt;

    public String getChapter_index() {
        return chapter_index;
    }

    public void setChapter_index(String chapter_index) {
        this.chapter_index = chapter_index;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isHasCache() {
        return hasCache;
    }

    public void setHasCache(boolean hasCache) {
        this.hasCache = hasCache;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public boolean isReadAuthority() {
        return readAuthority;
    }

    public void setReadAuthority(boolean readAuthority) {
        this.readAuthority = readAuthority;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DocPt getPt() {
        return pt;
    }

    public void setPt(DocPt pt) {
        this.pt = pt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "DocData{" +
                "chapter_index='" + chapter_index + '\'' +
                ", cid='" + cid + '\'' +
                ", content='" + content + '\'' +
                ", hasCache=" + hasCache +
                ", isLogin=" + isLogin +
                ", readAuthority=" + readAuthority +
                ", site='" + site + '\'' +
                ", url='" + url + '\'' +
                ", pt=" + pt +
                '}';
    }
}
