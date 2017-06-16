import me.michidk.simpletwitterbotapi.utils.KeywordUtils;
import org.junit.Test;

/**
 * Created by Michael Lohr on 15.06.2017.
 */
public class KeywordUtilsTest {

    @Test
    public void checkKeywordSimple() {
        assert KeywordUtils.checkWholeKeyword("asdf #prost asdf", "#prost");
    }

    @Test
    public void checkKeywordNoSpace() {
        assert !KeywordUtils.checkWholeKeyword("asdf #prostasdf", "#prost");
        assert !KeywordUtils.checkWholeKeyword("asdf#prost asdf", "#prost");
        assert !KeywordUtils.checkWholeKeyword("asdf#prostasdf", "#prost");
    }

    @Test
    public void checkKeywordNoSpaceStart() {
        assert !KeywordUtils.checkWholeKeyword("#prostasdf", "#prost");
    }

    @Test
    public void checkKeywordNoSpaceEnd() {
        assert !KeywordUtils.checkWholeKeyword("asdf#prost", "#prost");
    }

    @Test
    public void checkKeywordStart() {
        assert KeywordUtils.checkWholeKeyword("#prost asdf", "#prost");
    }

    @Test
    public void checkKeywordEnd() {
        assert KeywordUtils.checkWholeKeyword("asdf #prost", "#prost");
    }

    @Test
    public void checkKeywordOnly() {
        assert KeywordUtils.checkWholeKeyword("#prost", "#prost");
    }

    @Test
    public void checkKeywordPunctation() {
        assert KeywordUtils.checkWholeKeyword("test #prost!", "#prost");
    }

    @Test
    public void checkCaseInsensitve() {
        assert KeywordUtils.checkWholeKeyword("#ProSt", "#prost");
    }

    @Test
    public void checkKeywordTerminatedByPunctation() {
        assert KeywordUtils.checkWholeKeyword("#prost!asd", "#prost");
    }

}
