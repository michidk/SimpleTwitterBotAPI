import me.michidk.simpletwitterbotapi.filters.SingleWordFilter;
import org.junit.Test;

/**
 * Created by Michael Lohr on 15.06.2017.
 */
public class SingleWordFilterTest {

    @Test
    public void checkKeywordSimple() {
        assert SingleWordFilter.checkKeyword("asdf #prost asdf", "#prost");
    }

    @Test
    public void checkKeywordNoSpace() {
        assert !SingleWordFilter.checkKeyword("asdf #prostasdf", "#prost");
        assert !SingleWordFilter.checkKeyword("asdf#prost asdf", "#prost");
        assert !SingleWordFilter.checkKeyword("asdf#prostasdf", "#prost");
    }

    @Test
    public void checkKeywordNoSpaceStart() {
        assert !SingleWordFilter.checkKeyword("#prostasdf", "#prost");
    }

    @Test
    public void checkKeywordNoSpaceEnd() {
        assert !SingleWordFilter.checkKeyword("asdf#prost", "#prost");
    }

    @Test
    public void checkKeywordStart() {
        assert SingleWordFilter.checkKeyword("#prost asdf", "#prost");
    }

    @Test
    public void checkKeywordEnd() {
        assert SingleWordFilter.checkKeyword("asdf #prost", "#prost");
    }

    @Test
    public void checkKeywordOnly() {
        assert SingleWordFilter.checkKeyword("#prost", "#prost");
    }

    @Test
    public void checkKeywordPunctation() {
        assert SingleWordFilter.checkKeyword("test #prost!", "#prost");
    }

    @Test
    public void checkCaseInsensitve() {
        assert SingleWordFilter.checkKeyword("#ProSt", "#prost");
    }

    @Test
    public void checkKeywordTerminatedByPunctation() {
        assert SingleWordFilter.checkKeyword("#prost!asd", "#prost");
    }

}
