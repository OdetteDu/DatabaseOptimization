// $ANTLR 3.2 Sep 23, 2009 12:02:23 SQL.g 2013-03-25 22:50:57

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SQLLexer extends Lexer {
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int CharString=6;
    public static final int EOF=-1;
    public static final int T__9=9;
    public static final int Int=5;
    public static final int Identifier=4;
    public static final int Float=7;
    public static final int T__19=19;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__16=16;
    public static final int WS=8;
    public static final int T__15=15;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int T__10=10;

    // delegates
    // delegators

    public SQLLexer() {;} 
    public SQLLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public SQLLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "SQL.g"; }

    // $ANTLR start "T__9"
    public final void mT__9() throws RecognitionException {
        try {
            int _type = T__9;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:3:6: ( ';' )
            // SQL.g:3:8: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__9"

    // $ANTLR start "T__10"
    public final void mT__10() throws RecognitionException {
        try {
            int _type = T__10;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:4:7: ( 'quit' )
            // SQL.g:4:9: 'quit'
            {
            match("quit"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__10"

    // $ANTLR start "T__11"
    public final void mT__11() throws RecognitionException {
        try {
            int _type = T__11;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:5:7: ( 'select' )
            // SQL.g:5:9: 'select'
            {
            match("select"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__11"

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:6:7: ( 'from' )
            // SQL.g:6:9: 'from'
            {
            match("from"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:7:7: ( 'where' )
            // SQL.g:7:9: 'where'
            {
            match("where"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:8:7: ( 'group' )
            // SQL.g:8:9: 'group'
            {
            match("group"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:9:7: ( 'by' )
            // SQL.g:9:9: 'by'
            {
            match("by"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:10:7: ( '.' )
            // SQL.g:10:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:11:7: ( ',' )
            // SQL.g:11:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:12:7: ( 'as' )
            // SQL.g:12:9: 'as'
            {
            match("as"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:13:7: ( 'and' )
            // SQL.g:13:9: 'and'
            {
            match("and"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:14:7: ( '(' )
            // SQL.g:14:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:15:7: ( 'or' )
            // SQL.g:15:9: 'or'
            {
            match("or"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:16:7: ( ')' )
            // SQL.g:16:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:17:7: ( '=' )
            // SQL.g:17:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:18:7: ( '<' )
            // SQL.g:18:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:19:7: ( '>' )
            // SQL.g:19:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:20:7: ( 'not' )
            // SQL.g:20:9: 'not'
            {
            match("not"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:21:7: ( 'sum' )
            // SQL.g:21:9: 'sum'
            {
            match("sum"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:22:7: ( 'avg' )
            // SQL.g:22:9: 'avg'
            {
            match("avg"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:23:7: ( '+' )
            // SQL.g:23:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:24:7: ( '-' )
            // SQL.g:24:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:25:7: ( '*' )
            // SQL.g:25:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:26:7: ( '/' )
            // SQL.g:26:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "Float"
    public final void mFloat() throws RecognitionException {
        try {
            int _type = Float;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:164:2: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )+ ( 'e' ( '-' )? ( '0' .. '9' )+ )? )
            // SQL.g:164:4: ( '0' .. '9' )+ '.' ( '0' .. '9' )+ ( 'e' ( '-' )? ( '0' .. '9' )+ )?
            {
            // SQL.g:164:4: ( '0' .. '9' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // SQL.g:164:5: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

            match('.'); 
            // SQL.g:164:20: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // SQL.g:164:21: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);

            // SQL.g:164:32: ( 'e' ( '-' )? ( '0' .. '9' )+ )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='e') ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // SQL.g:164:33: 'e' ( '-' )? ( '0' .. '9' )+
                    {
                    match('e'); 
                    // SQL.g:164:37: ( '-' )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0=='-') ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // SQL.g:164:37: '-'
                            {
                            match('-'); 

                            }
                            break;

                    }

                    // SQL.g:164:42: ( '0' .. '9' )+
                    int cnt4=0;
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // SQL.g:164:43: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt4 >= 1 ) break loop4;
                                EarlyExitException eee =
                                    new EarlyExitException(4, input);
                                throw eee;
                        }
                        cnt4++;
                    } while (true);


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Float"

    // $ANTLR start "Identifier"
    public final void mIdentifier() throws RecognitionException {
        try {
            int _type = Identifier;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:168:2: ( ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '_' ) ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '_' | ( '0' .. '9' ) )* )
            // SQL.g:168:4: ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '_' ) ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '_' | ( '0' .. '9' ) )*
            {
            // SQL.g:168:4: ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '_' )
            int alt6=3;
            switch ( input.LA(1) ) {
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'l':
            case 'm':
            case 'n':
            case 'o':
            case 'p':
            case 'q':
            case 'r':
            case 's':
            case 't':
            case 'u':
            case 'v':
            case 'w':
            case 'x':
            case 'y':
            case 'z':
                {
                alt6=1;
                }
                break;
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
                {
                alt6=2;
                }
                break;
            case '_':
                {
                alt6=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // SQL.g:168:5: ( 'a' .. 'z' )
                    {
                    // SQL.g:168:5: ( 'a' .. 'z' )
                    // SQL.g:168:6: 'a' .. 'z'
                    {
                    matchRange('a','z'); 

                    }


                    }
                    break;
                case 2 :
                    // SQL.g:168:18: ( 'A' .. 'Z' )
                    {
                    // SQL.g:168:18: ( 'A' .. 'Z' )
                    // SQL.g:168:19: 'A' .. 'Z'
                    {
                    matchRange('A','Z'); 

                    }


                    }
                    break;
                case 3 :
                    // SQL.g:168:31: '_'
                    {
                    match('_'); 

                    }
                    break;

            }

            // SQL.g:168:36: ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '_' | ( '0' .. '9' ) )*
            loop7:
            do {
                int alt7=5;
                switch ( input.LA(1) ) {
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    {
                    alt7=1;
                    }
                    break;
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    {
                    alt7=2;
                    }
                    break;
                case '_':
                    {
                    alt7=3;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    {
                    alt7=4;
                    }
                    break;

                }

                switch (alt7) {
            	case 1 :
            	    // SQL.g:168:37: ( 'a' .. 'z' )
            	    {
            	    // SQL.g:168:37: ( 'a' .. 'z' )
            	    // SQL.g:168:38: 'a' .. 'z'
            	    {
            	    matchRange('a','z'); 

            	    }


            	    }
            	    break;
            	case 2 :
            	    // SQL.g:168:50: ( 'A' .. 'Z' )
            	    {
            	    // SQL.g:168:50: ( 'A' .. 'Z' )
            	    // SQL.g:168:51: 'A' .. 'Z'
            	    {
            	    matchRange('A','Z'); 

            	    }


            	    }
            	    break;
            	case 3 :
            	    // SQL.g:168:63: '_'
            	    {
            	    match('_'); 

            	    }
            	    break;
            	case 4 :
            	    // SQL.g:168:69: ( '0' .. '9' )
            	    {
            	    // SQL.g:168:69: ( '0' .. '9' )
            	    // SQL.g:168:70: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Identifier"

    // $ANTLR start "Int"
    public final void mInt() throws RecognitionException {
        try {
            int _type = Int;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:172:2: ( ( '0' .. '9' )+ )
            // SQL.g:172:4: ( '0' .. '9' )+
            {
            // SQL.g:172:4: ( '0' .. '9' )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='0' && LA8_0<='9')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // SQL.g:172:5: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Int"

    // $ANTLR start "CharString"
    public final void mCharString() throws RecognitionException {
        try {
            int _type = CharString;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:176:2: ( '\"' ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | ' ' | ( '0' .. '9' ) )* '\"' )
            // SQL.g:176:4: '\"' ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | ' ' | ( '0' .. '9' ) )* '\"'
            {
            match('\"'); 
            // SQL.g:176:8: ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | ' ' | ( '0' .. '9' ) )*
            loop9:
            do {
                int alt9=5;
                switch ( input.LA(1) ) {
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    {
                    alt9=1;
                    }
                    break;
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    {
                    alt9=2;
                    }
                    break;
                case ' ':
                    {
                    alt9=3;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    {
                    alt9=4;
                    }
                    break;

                }

                switch (alt9) {
            	case 1 :
            	    // SQL.g:176:9: ( 'a' .. 'z' )
            	    {
            	    // SQL.g:176:9: ( 'a' .. 'z' )
            	    // SQL.g:176:10: 'a' .. 'z'
            	    {
            	    matchRange('a','z'); 

            	    }


            	    }
            	    break;
            	case 2 :
            	    // SQL.g:176:22: ( 'A' .. 'Z' )
            	    {
            	    // SQL.g:176:22: ( 'A' .. 'Z' )
            	    // SQL.g:176:23: 'A' .. 'Z'
            	    {
            	    matchRange('A','Z'); 

            	    }


            	    }
            	    break;
            	case 3 :
            	    // SQL.g:176:35: ' '
            	    {
            	    match(' '); 

            	    }
            	    break;
            	case 4 :
            	    // SQL.g:176:41: ( '0' .. '9' )
            	    {
            	    // SQL.g:176:41: ( '0' .. '9' )
            	    // SQL.g:176:42: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            match('\"'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CharString"

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SQL.g:180:2: ( ( ' ' | '\\t' | '\\r' | '\\n' )* )
            // SQL.g:180:4: ( ' ' | '\\t' | '\\r' | '\\n' )*
            {
            // SQL.g:180:4: ( ' ' | '\\t' | '\\r' | '\\n' )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='\t' && LA10_0<='\n')||LA10_0=='\r'||LA10_0==' ') ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // SQL.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            _channel=HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WS"

    public void mTokens() throws RecognitionException {
        // SQL.g:1:8: ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | Float | Identifier | Int | CharString | WS )
        int alt11=29;
        alt11 = dfa11.predict(input);
        switch (alt11) {
            case 1 :
                // SQL.g:1:10: T__9
                {
                mT__9(); 

                }
                break;
            case 2 :
                // SQL.g:1:15: T__10
                {
                mT__10(); 

                }
                break;
            case 3 :
                // SQL.g:1:21: T__11
                {
                mT__11(); 

                }
                break;
            case 4 :
                // SQL.g:1:27: T__12
                {
                mT__12(); 

                }
                break;
            case 5 :
                // SQL.g:1:33: T__13
                {
                mT__13(); 

                }
                break;
            case 6 :
                // SQL.g:1:39: T__14
                {
                mT__14(); 

                }
                break;
            case 7 :
                // SQL.g:1:45: T__15
                {
                mT__15(); 

                }
                break;
            case 8 :
                // SQL.g:1:51: T__16
                {
                mT__16(); 

                }
                break;
            case 9 :
                // SQL.g:1:57: T__17
                {
                mT__17(); 

                }
                break;
            case 10 :
                // SQL.g:1:63: T__18
                {
                mT__18(); 

                }
                break;
            case 11 :
                // SQL.g:1:69: T__19
                {
                mT__19(); 

                }
                break;
            case 12 :
                // SQL.g:1:75: T__20
                {
                mT__20(); 

                }
                break;
            case 13 :
                // SQL.g:1:81: T__21
                {
                mT__21(); 

                }
                break;
            case 14 :
                // SQL.g:1:87: T__22
                {
                mT__22(); 

                }
                break;
            case 15 :
                // SQL.g:1:93: T__23
                {
                mT__23(); 

                }
                break;
            case 16 :
                // SQL.g:1:99: T__24
                {
                mT__24(); 

                }
                break;
            case 17 :
                // SQL.g:1:105: T__25
                {
                mT__25(); 

                }
                break;
            case 18 :
                // SQL.g:1:111: T__26
                {
                mT__26(); 

                }
                break;
            case 19 :
                // SQL.g:1:117: T__27
                {
                mT__27(); 

                }
                break;
            case 20 :
                // SQL.g:1:123: T__28
                {
                mT__28(); 

                }
                break;
            case 21 :
                // SQL.g:1:129: T__29
                {
                mT__29(); 

                }
                break;
            case 22 :
                // SQL.g:1:135: T__30
                {
                mT__30(); 

                }
                break;
            case 23 :
                // SQL.g:1:141: T__31
                {
                mT__31(); 

                }
                break;
            case 24 :
                // SQL.g:1:147: T__32
                {
                mT__32(); 

                }
                break;
            case 25 :
                // SQL.g:1:153: Float
                {
                mFloat(); 

                }
                break;
            case 26 :
                // SQL.g:1:159: Identifier
                {
                mIdentifier(); 

                }
                break;
            case 27 :
                // SQL.g:1:170: Int
                {
                mInt(); 

                }
                break;
            case 28 :
                // SQL.g:1:174: CharString
                {
                mCharString(); 

                }
                break;
            case 29 :
                // SQL.g:1:185: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA11 dfa11 = new DFA11(this);
    static final String DFA11_eotS =
        "\1\31\1\uffff\6\27\2\uffff\1\27\1\uffff\1\27\4\uffff\1\27\4\uffff"+
        "\1\46\3\uffff\6\27\1\56\1\57\2\27\1\62\1\27\2\uffff\2\27\1\66\3"+
        "\27\2\uffff\1\72\1\73\1\uffff\1\74\1\75\1\27\1\uffff\1\77\2\27\4"+
        "\uffff\1\27\1\uffff\1\103\1\104\1\105\3\uffff";
    static final String DFA11_eofS =
        "\106\uffff";
    static final String DFA11_minS =
        "\1\42\1\uffff\1\165\1\145\1\162\1\150\1\162\1\171\2\uffff\1\156"+
        "\1\uffff\1\162\4\uffff\1\157\4\uffff\1\56\3\uffff\1\151\1\154\1"+
        "\155\1\157\1\145\1\157\2\60\1\144\1\147\1\60\1\164\2\uffff\1\164"+
        "\1\145\1\60\1\155\1\162\1\165\2\uffff\2\60\1\uffff\2\60\1\143\1"+
        "\uffff\1\60\1\145\1\160\4\uffff\1\164\1\uffff\3\60\3\uffff";
    static final String DFA11_maxS =
        "\1\172\1\uffff\2\165\1\162\1\150\1\162\1\171\2\uffff\1\166\1\uffff"+
        "\1\162\4\uffff\1\157\4\uffff\1\71\3\uffff\1\151\1\154\1\155\1\157"+
        "\1\145\1\157\2\172\1\144\1\147\1\172\1\164\2\uffff\1\164\1\145\1"+
        "\172\1\155\1\162\1\165\2\uffff\2\172\1\uffff\2\172\1\143\1\uffff"+
        "\1\172\1\145\1\160\4\uffff\1\164\1\uffff\3\172\3\uffff";
    static final String DFA11_acceptS =
        "\1\uffff\1\1\6\uffff\1\10\1\11\1\uffff\1\14\1\uffff\1\16\1\17\1"+
        "\20\1\21\1\uffff\1\25\1\26\1\27\1\30\1\uffff\1\32\1\34\1\35\14\uffff"+
        "\1\33\1\31\6\uffff\1\7\1\12\2\uffff\1\15\3\uffff\1\23\3\uffff\1"+
        "\13\1\24\1\22\1\2\1\uffff\1\4\3\uffff\1\5\1\6\1\3";
    static final String DFA11_specialS =
        "\106\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\30\5\uffff\1\13\1\15\1\24\1\22\1\11\1\23\1\10\1\25\12\26"+
            "\1\uffff\1\1\1\17\1\16\1\20\2\uffff\32\27\4\uffff\1\27\1\uffff"+
            "\1\12\1\7\3\27\1\4\1\6\6\27\1\21\1\14\1\27\1\2\1\27\1\3\3\27"+
            "\1\5\3\27",
            "",
            "\1\32",
            "\1\33\17\uffff\1\34",
            "\1\35",
            "\1\36",
            "\1\37",
            "\1\40",
            "",
            "",
            "\1\42\4\uffff\1\41\2\uffff\1\43",
            "",
            "\1\44",
            "",
            "",
            "",
            "",
            "\1\45",
            "",
            "",
            "",
            "",
            "\1\47\1\uffff\12\26",
            "",
            "",
            "",
            "\1\50",
            "\1\51",
            "\1\52",
            "\1\53",
            "\1\54",
            "\1\55",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\60",
            "\1\61",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\63",
            "",
            "",
            "\1\64",
            "\1\65",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\67",
            "\1\70",
            "\1\71",
            "",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\76",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\100",
            "\1\101",
            "",
            "",
            "",
            "",
            "\1\102",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "",
            ""
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | Float | Identifier | Int | CharString | WS );";
        }
    }
 

}