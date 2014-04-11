// $ANTLR 3.2 Sep 23, 2009 12:02:23 SQL.g 2013-03-25 22:50:56


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SQLParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "Identifier", "Int", "CharString", "Float", "WS", "';'", "'quit'", "'select'", "'from'", "'where'", "'group'", "'by'", "'.'", "','", "'as'", "'and'", "'('", "'or'", "')'", "'='", "'<'", "'>'", "'not'", "'sum'", "'avg'", "'+'", "'-'", "'*'", "'/'"
	};
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
	public static final int T__30=30;
	public static final int T__19=19;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int WS=8;
	public static final int T__16=16;
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


	public SQLParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public SQLParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);

	}


	public String[] getTokenNames() { return SQLParser.tokenNames; }
	public String getGrammarFileName() { return "SQL.g"; }



	// this is the list of expressions specified in the query's SELECT clause
	// that we need to return to the user
	private ArrayList <Expression> exprsToSelect = new ArrayList <Expression> ();

	// this is a map where for each entry in the map, the key is an identifier
	// that was found in the FROM clause, and the value is a table name that
	// was found there... for example, if we have FROM myTable AS a, then the pair
	// ("a", "myTable") would be put into this map
	private Map <String, String> tables = new HashMap <String, String> ();

	// this is the boolean expression in the WHERE clause
	private Expression whereClause = null;

	// this is the attribute that we are grouping by, if any (we only allow one!)
	private String groupingAtt = null;

	// returns the list of expressions from the SELECT statement
	public ArrayList <Expression> getSELECT () {
		return exprsToSelect;
	}

	// returns the map representing the FROM clause
	public Map <String, String> getFROM () {
		return tables;
	}

	// returns the expression in the WHERE clause
	public Expression getWHERE () {
		return whereClause;
	}

	// returns the grouping attribute
	public String getGROUPBY () {
		return groupingAtt;
	}



	// $ANTLR start "parse"
	// SQL.g:51:1: parse returns [boolean value] : ( selectClause fromClause whereClause groupingClause ';' | 'quit' ';' );
	public final boolean parse() throws RecognitionException {
		boolean value = false;

		try {
			// SQL.g:52:2: ( selectClause fromClause whereClause groupingClause ';' | 'quit' ';' )
			int alt1=2;
			int LA1_0 = input.LA(1);

			if ( (LA1_0==11) ) {
				alt1=1;
			}
			else if ( (LA1_0==10) ) {
				alt1=2;
			}
			else {
				NoViableAltException nvae =
						new NoViableAltException("", 1, 0, input);

				throw nvae;
			}
			switch (alt1) {
			case 1 :
				// SQL.g:52:4: selectClause fromClause whereClause groupingClause ';'
			{
				pushFollow(FOLLOW_selectClause_in_parse31);
				selectClause();

				state._fsp--;

				pushFollow(FOLLOW_fromClause_in_parse33);
				fromClause();

				state._fsp--;

				pushFollow(FOLLOW_whereClause_in_parse35);
				whereClause();

				state._fsp--;

				pushFollow(FOLLOW_groupingClause_in_parse37);
				groupingClause();

				state._fsp--;

				match(input,9,FOLLOW_9_in_parse39); 
				value = true;

			}
			break;
			case 2 :
				// SQL.g:53:4: 'quit' ';'
			{
				match(input,10,FOLLOW_10_in_parse46); 
				match(input,9,FOLLOW_9_in_parse48); 
				value = false;

			}
			break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		return value;
	}
	// $ANTLR end "parse"


	// $ANTLR start "selectClause"
	// SQL.g:56:1: selectClause : 'select' exprList ;
	public final void selectClause() throws RecognitionException {
		try {
			// SQL.g:57:2: ( 'select' exprList )
			// SQL.g:57:4: 'select' exprList
			{
				match(input,11,FOLLOW_11_in_selectClause105); 
				pushFollow(FOLLOW_exprList_in_selectClause107);
				exprList();

				state._fsp--;


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		return ;
	}
	// $ANTLR end "selectClause"


	// $ANTLR start "fromClause"
	// SQL.g:60:1: fromClause : 'from' fromList ;
	public final void fromClause() throws RecognitionException {
		try {
			// SQL.g:61:2: ( 'from' fromList )
			// SQL.g:61:4: 'from' fromList
			{
				match(input,12,FOLLOW_12_in_fromClause118); 
				pushFollow(FOLLOW_fromList_in_fromClause120);
				fromList();

				state._fsp--;


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		return ;
	}
	// $ANTLR end "fromClause"


	// $ANTLR start "whereClause"
	// SQL.g:64:1: whereClause : ( 'where' c= cnfExp | );
	public final void whereClause() throws RecognitionException {
		Expression c = null;


		try {
			// SQL.g:65:2: ( 'where' c= cnfExp | )
			int alt2=2;
			int LA2_0 = input.LA(1);

			if ( (LA2_0==13) ) {
				alt2=1;
			}
			else if ( (LA2_0==9||LA2_0==14) ) {
				alt2=2;
			}
			else {
				NoViableAltException nvae =
						new NoViableAltException("", 2, 0, input);

				throw nvae;
			}
			switch (alt2) {
			case 1 :
				// SQL.g:65:4: 'where' c= cnfExp
			{
				match(input,13,FOLLOW_13_in_whereClause131); 
				pushFollow(FOLLOW_cnfExp_in_whereClause135);
				c=cnfExp();

				state._fsp--;

				whereClause = c;

			}
			break;
			case 2 :
				// SQL.g:67:2: 
			{
			}
			break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		return ;
	}
	// $ANTLR end "whereClause"


	// $ANTLR start "groupingClause"
	// SQL.g:69:1: groupingClause : ( 'group' 'by' i1= Identifier '.' i2= Identifier | );
	public final void groupingClause() throws RecognitionException {
		Token i1=null;
		Token i2=null;

		try {
			// SQL.g:70:2: ( 'group' 'by' i1= Identifier '.' i2= Identifier | )
			int alt3=2;
			int LA3_0 = input.LA(1);

			if ( (LA3_0==14) ) {
				alt3=1;
			}
			else if ( (LA3_0==9) ) {
				alt3=2;
			}
			else {
				NoViableAltException nvae =
						new NoViableAltException("", 3, 0, input);

				throw nvae;
			}
			switch (alt3) {
			case 1 :
				// SQL.g:70:4: 'group' 'by' i1= Identifier '.' i2= Identifier
			{
				match(input,14,FOLLOW_14_in_groupingClause151); 
				match(input,15,FOLLOW_15_in_groupingClause153); 
				i1=(Token)match(input,Identifier,FOLLOW_Identifier_in_groupingClause157); 
				match(input,16,FOLLOW_16_in_groupingClause159); 
				i2=(Token)match(input,Identifier,FOLLOW_Identifier_in_groupingClause163); 
				groupingAtt = (i1!=null?i1.getText():null) + "." + (i2!=null?i2.getText():null);

			}
			break;
			case 2 :
				// SQL.g:72:2: 
			{
			}
			break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		return ;
	}
	// $ANTLR end "groupingClause"


	// $ANTLR start "exprList"
	// SQL.g:74:1: exprList : e1= exp ( ',' e2= exp )* ;
	public final void exprList() throws RecognitionException {
		Expression e1 = null;

		Expression e2 = null;


		try {
			// SQL.g:75:2: (e1= exp ( ',' e2= exp )* )
			// SQL.g:75:4: e1= exp ( ',' e2= exp )*
			{
				pushFollow(FOLLOW_exp_in_exprList181);
				e1=exp();

				state._fsp--;

				exprsToSelect.add (e1);
				// SQL.g:76:3: ( ',' e2= exp )*
				loop4:
					do {
						int alt4=2;
						int LA4_0 = input.LA(1);

						if ( (LA4_0==17) ) {
							alt4=1;
						}


						switch (alt4) {
						case 1 :
							// SQL.g:76:5: ',' e2= exp
							{
							match(input,17,FOLLOW_17_in_exprList195); 
							pushFollow(FOLLOW_exp_in_exprList199);
							e2=exp();

							state._fsp--;

							exprsToSelect.add (e2);

							}
							break;

						default :
							break loop4;
						}
					} while (true);


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		return ;
	}
	// $ANTLR end "exprList"


	// $ANTLR start "fromList"
	// SQL.g:80:1: fromList : i1= Identifier 'as' i2= Identifier ( ',' i3= Identifier 'as' i4= Identifier )* ;
	public final void fromList() throws RecognitionException {
		Token i1=null;
		Token i2=null;
		Token i3=null;
		Token i4=null;

		try {
			// SQL.g:81:2: (i1= Identifier 'as' i2= Identifier ( ',' i3= Identifier 'as' i4= Identifier )* )
			// SQL.g:81:4: i1= Identifier 'as' i2= Identifier ( ',' i3= Identifier 'as' i4= Identifier )*
			{
				i1=(Token)match(input,Identifier,FOLLOW_Identifier_in_fromList220); 
				match(input,18,FOLLOW_18_in_fromList222); 
				i2=(Token)match(input,Identifier,FOLLOW_Identifier_in_fromList226); 
				tables.put ((i2!=null?i2.getText():null), (i1!=null?i1.getText():null));
				// SQL.g:82:3: ( ',' i3= Identifier 'as' i4= Identifier )*
				loop5:
					do {
						int alt5=2;
						int LA5_0 = input.LA(1);

						if ( (LA5_0==17) ) {
							alt5=1;
						}


						switch (alt5) {
						case 1 :
							// SQL.g:82:5: ',' i3= Identifier 'as' i4= Identifier
							{
							match(input,17,FOLLOW_17_in_fromList240); 
							i3=(Token)match(input,Identifier,FOLLOW_Identifier_in_fromList244); 
							match(input,18,FOLLOW_18_in_fromList246); 
							i4=(Token)match(input,Identifier,FOLLOW_Identifier_in_fromList250); 
							tables.put ((i4!=null?i4.getText():null), (i3!=null?i3.getText():null));

							}
							break;

						default :
							break loop5;
						}
					} while (true);


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		return ;
	}
	// $ANTLR end "fromList"


	// $ANTLR start "cnfExp"
	// SQL.g:86:1: cnfExp returns [Expression value] : d1= disjunction ( 'and' d2= disjunction )* ;
	public final Expression cnfExp() throws RecognitionException {
		Expression value = null;

		Expression d1 = null;

		Expression d2 = null;


		try {
			// SQL.g:87:2: (d1= disjunction ( 'and' d2= disjunction )* )
			// SQL.g:87:4: d1= disjunction ( 'and' d2= disjunction )*
			{
				pushFollow(FOLLOW_disjunction_in_cnfExp275);
				d1=disjunction();

				state._fsp--;

				value = d1;
				// SQL.g:88:3: ( 'and' d2= disjunction )*
				loop6:
					do {
						int alt6=2;
						int LA6_0 = input.LA(1);

						if ( (LA6_0==19) ) {
							alt6=1;
						}


						switch (alt6) {
						case 1 :
							// SQL.g:88:5: 'and' d2= disjunction
							{
							match(input,19,FOLLOW_19_in_cnfExp285); 
							pushFollow(FOLLOW_disjunction_in_cnfExp289);
							d2=disjunction();

							state._fsp--;

							Expression temp = value;
							value = new Expression ("and");
							value.setSubexpression (temp, d2);

							}
							break;

						default :
							break loop6;
						}
					} while (true);


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		return value;
	}
	// $ANTLR end "cnfExp"


	// $ANTLR start "disjunction"
	// SQL.g:94:1: disjunction returns [Expression value] : '(' c1= comparison ( 'or' c2= comparison )* ')' ;
	public final Expression disjunction() throws RecognitionException {
		Expression value = null;

		Expression c1 = null;

		Expression c2 = null;


		try {
			// SQL.g:95:2: ( '(' c1= comparison ( 'or' c2= comparison )* ')' )
			// SQL.g:95:4: '(' c1= comparison ( 'or' c2= comparison )* ')'
			{
				match(input,20,FOLLOW_20_in_disjunction311); 
				pushFollow(FOLLOW_comparison_in_disjunction315);
				c1=comparison();

				state._fsp--;

				value = c1;
				// SQL.g:96:3: ( 'or' c2= comparison )*
				loop7:
					do {
						int alt7=2;
						int LA7_0 = input.LA(1);

						if ( (LA7_0==21) ) {
							alt7=1;
						}


						switch (alt7) {
						case 1 :
							// SQL.g:96:5: 'or' c2= comparison
							{
							match(input,21,FOLLOW_21_in_disjunction324); 
							pushFollow(FOLLOW_comparison_in_disjunction328);
							c2=comparison();

							state._fsp--;

							Expression temp = value;
							value = new Expression ("or");
							value.setSubexpression (temp, c2);

							}
							break;

						default :
							break loop7;
						}
					} while (true);

				match(input,22,FOLLOW_22_in_disjunction337); 

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		return value;
	}
	// $ANTLR end "disjunction"


	// $ANTLR start "comparison"
	// SQL.g:102:1: comparison returns [Expression value] : (e1= addExp ( '=' e2= addExp | '<' e2= addExp | '>' e2= addExp ) | 'not' c= comparison );
	public final Expression comparison() throws RecognitionException {
		Expression value = null;

		Expression e1 = null;

		Expression e2 = null;

		Expression c = null;


		try {
			// SQL.g:103:2: (e1= addExp ( '=' e2= addExp | '<' e2= addExp | '>' e2= addExp ) | 'not' c= comparison )
			int alt9=2;
			int LA9_0 = input.LA(1);

			if ( ((LA9_0>=Identifier && LA9_0<=Float)||LA9_0==20||LA9_0==30) ) {
				alt9=1;
			}
			else if ( (LA9_0==26) ) {
				alt9=2;
			}
			else {
				NoViableAltException nvae =
						new NoViableAltException("", 9, 0, input);

				throw nvae;
			}
			switch (alt9) {
			case 1 :
				// SQL.g:103:4: e1= addExp ( '=' e2= addExp | '<' e2= addExp | '>' e2= addExp )
			{
				pushFollow(FOLLOW_addExp_in_comparison354);
				e1=addExp();

				state._fsp--;

				value = e1;
				// SQL.g:104:3: ( '=' e2= addExp | '<' e2= addExp | '>' e2= addExp )
				int alt8=3;
				switch ( input.LA(1) ) {
				case 23:
				{
					alt8=1;
				}
				break;
				case 24:
				{
					alt8=2;
				}
				break;
				case 25:
				{
					alt8=3;
				}
				break;
				default:
					NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);

					throw nvae;
				}

				switch (alt8) {
				case 1 :
					// SQL.g:104:5: '=' e2= addExp
					{
					match(input,23,FOLLOW_23_in_comparison363); 
					pushFollow(FOLLOW_addExp_in_comparison367);
					e2=addExp();

					state._fsp--;

					Expression temp = value;
					value = new Expression ("equals");
					value.setSubexpression (temp, e2);

					}
					break;
				case 2 :
					// SQL.g:107:5: '<' e2= addExp
				{
					match(input,24,FOLLOW_24_in_comparison375); 
					pushFollow(FOLLOW_addExp_in_comparison379);
					e2=addExp();

					state._fsp--;

					Expression temp = value;
					value = new Expression ("less than");
					value.setSubexpression (temp, e2);

				}
				break;
				case 3 :
					// SQL.g:110:5: '>' e2= addExp
				{
					match(input,25,FOLLOW_25_in_comparison387); 
					pushFollow(FOLLOW_addExp_in_comparison391);
					e2=addExp();

					state._fsp--;

					Expression temp = value;
					value = new Expression ("greater than");
					value.setSubexpression (temp, e2);

				}
				break;

				}


			}
			break;
			case 2 :
				// SQL.g:114:5: 'not' c= comparison
			{
				match(input,26,FOLLOW_26_in_comparison403); 
				pushFollow(FOLLOW_comparison_in_comparison407);
				c=comparison();

				state._fsp--;

				value = new Expression ("not");
				value.setSubexpression (c);

			}
			break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		return value;
	}
	// $ANTLR end "comparison"


	// $ANTLR start "exp"
	// SQL.g:118:1: exp returns [Expression value] : ( 'sum' '(' e1= addExp ')' | 'avg' '(' e1= addExp ')' | e1= addExp );
	public final Expression exp() throws RecognitionException {
		Expression value = null;

		Expression e1 = null;


		try {
			// SQL.g:119:2: ( 'sum' '(' e1= addExp ')' | 'avg' '(' e1= addExp ')' | e1= addExp )
			int alt10=3;
			switch ( input.LA(1) ) {
			case 27:
			{
				alt10=1;
			}
			break;
			case 28:
			{
				alt10=2;
			}
			break;
			case Identifier:
			case Int:
			case CharString:
			case Float:
			case 20:
			case 30:
			{
				alt10=3;
			}
			break;
			default:
				NoViableAltException nvae =
				new NoViableAltException("", 10, 0, input);

				throw nvae;
			}

			switch (alt10) {
			case 1 :
				// SQL.g:119:4: 'sum' '(' e1= addExp ')'
				{
				match(input,27,FOLLOW_27_in_exp424); 
				match(input,20,FOLLOW_20_in_exp426); 
				pushFollow(FOLLOW_addExp_in_exp430);
				e1=addExp();

				state._fsp--;

				match(input,22,FOLLOW_22_in_exp432); 
				value = new Expression ("sum");
				value.setSubexpression (e1);

				}
				break;
			case 2 :
				// SQL.g:121:4: 'avg' '(' e1= addExp ')'
			{
				match(input,28,FOLLOW_28_in_exp439); 
				match(input,20,FOLLOW_20_in_exp441); 
				pushFollow(FOLLOW_addExp_in_exp445);
				e1=addExp();

				state._fsp--;

				match(input,22,FOLLOW_22_in_exp447); 
				value = new Expression ("avg");
				value.setSubexpression (e1);

			}
			break;
			case 3 :
				// SQL.g:123:4: e1= addExp
			{
				pushFollow(FOLLOW_addExp_in_exp456);
				e1=addExp();

				state._fsp--;

				value = e1;

			}
			break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		return value;
	}
	// $ANTLR end "exp"


	// $ANTLR start "addExp"
	// SQL.g:126:1: addExp returns [Expression value] : m1= multExp ( '+' m2= multExp | '-' m2= multExp )* ;
	public final Expression addExp() throws RecognitionException {
		Expression value = null;

		Expression m1 = null;

		Expression m2 = null;


		try {
			// SQL.g:127:2: (m1= multExp ( '+' m2= multExp | '-' m2= multExp )* )
			// SQL.g:127:4: m1= multExp ( '+' m2= multExp | '-' m2= multExp )*
			{
				pushFollow(FOLLOW_multExp_in_addExp489);
				m1=multExp();

				state._fsp--;

				value = m1;
				// SQL.g:128:3: ( '+' m2= multExp | '-' m2= multExp )*
				loop11:
					do {
						int alt11=3;
						int LA11_0 = input.LA(1);

						if ( (LA11_0==29) ) {
							alt11=1;
						}
						else if ( (LA11_0==30) ) {
							alt11=2;
						}


						switch (alt11) {
						case 1 :
							// SQL.g:128:5: '+' m2= multExp
							{
							match(input,29,FOLLOW_29_in_addExp506); 
							pushFollow(FOLLOW_multExp_in_addExp510);
							m2=multExp();

							state._fsp--;

							Expression temp = value;
							value = new Expression ("plus");
							value.setSubexpression (temp, m2);

							}
							break;
						case 2 :
							// SQL.g:131:5: '-' m2= multExp
						{
							match(input,30,FOLLOW_30_in_addExp521); 
							pushFollow(FOLLOW_multExp_in_addExp525);
							m2=multExp();

							state._fsp--;

							Expression temp = value;
							value = new Expression ("minus");
							value.setSubexpression (temp, m2);

						}
						break;

						default :
							break loop11;
						}
					} while (true);


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		return value;
	}
	// $ANTLR end "addExp"


	// $ANTLR start "multExp"
	// SQL.g:137:1: multExp returns [Expression value] : m1= atomExp ( '*' m2= atomExp | '/' m2= atomExp )* ;
	public final Expression multExp() throws RecognitionException {
		Expression value = null;

		Expression m1 = null;

		Expression m2 = null;


		try {
			// SQL.g:138:2: (m1= atomExp ( '*' m2= atomExp | '/' m2= atomExp )* )
			// SQL.g:138:4: m1= atomExp ( '*' m2= atomExp | '/' m2= atomExp )*
			{
				pushFollow(FOLLOW_atomExp_in_multExp552);
				m1=atomExp();

				state._fsp--;

				value = m1;
				// SQL.g:139:3: ( '*' m2= atomExp | '/' m2= atomExp )*
				loop12:
					do {
						int alt12=3;
						int LA12_0 = input.LA(1);

						if ( (LA12_0==31) ) {
							alt12=1;
						}
						else if ( (LA12_0==32) ) {
							alt12=2;
						}


						switch (alt12) {
						case 1 :
							// SQL.g:139:5: '*' m2= atomExp
							{
							match(input,31,FOLLOW_31_in_multExp569); 
							pushFollow(FOLLOW_atomExp_in_multExp573);
							m2=atomExp();

							state._fsp--;

							Expression temp = value;
							value = new Expression ("times");
							value.setSubexpression (temp, m2);

							}
							break;
						case 2 :
							// SQL.g:142:5: '/' m2= atomExp
						{
							match(input,32,FOLLOW_32_in_multExp584); 
							pushFollow(FOLLOW_atomExp_in_multExp588);
							m2=atomExp();

							state._fsp--;

							Expression temp = value;
							value = new Expression ("divided by");
							value.setSubexpression (temp, m2);

						}
						break;

						default :
							break loop12;
						}
					} while (true);


			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		return value;
	}
	// $ANTLR end "multExp"


	// $ANTLR start "atomExp"
	// SQL.g:148:1: atomExp returns [Expression value] : (n= Int | i1= Identifier '.' i2= Identifier | c= CharString | c= Float | '(' e1= addExp ')' | '-' atomExp );
	public final Expression atomExp() throws RecognitionException {
		Expression value = null;

		Token n=null;
		Token i1=null;
		Token i2=null;
		Token c=null;
		Expression e1 = null;


		try {
			// SQL.g:149:2: (n= Int | i1= Identifier '.' i2= Identifier | c= CharString | c= Float | '(' e1= addExp ')' | '-' atomExp )
			int alt13=6;
			switch ( input.LA(1) ) {
			case Int:
			{
				alt13=1;
			}
			break;
			case Identifier:
			{
				alt13=2;
			}
			break;
			case CharString:
			{
				alt13=3;
			}
			break;
			case Float:
			{
				alt13=4;
			}
			break;
			case 20:
			{
				alt13=5;
			}
			break;
			case 30:
			{
				alt13=6;
			}
			break;
			default:
				NoViableAltException nvae =
				new NoViableAltException("", 13, 0, input);

				throw nvae;
			}

			switch (alt13) {
			case 1 :
				// SQL.g:149:4: n= Int
				{
				n=(Token)match(input,Int,FOLLOW_Int_in_atomExp615); 
				value = new Expression ("literal int");
				value.setValue ((n!=null?n.getText():null));

				}
			break;
			case 2 :
				// SQL.g:151:4: i1= Identifier '.' i2= Identifier
				{
				i1=(Token)match(input,Identifier,FOLLOW_Identifier_in_atomExp631); 
				match(input,16,FOLLOW_16_in_atomExp633); 
				i2=(Token)match(input,Identifier,FOLLOW_Identifier_in_atomExp637); 
				value = new Expression ("identifier");
				value.setValue ((i1!=null?i1.getText():null) + "." + (i2!=null?i2.getText():null));

				}
				break;
			case 3 :
				// SQL.g:153:4: c= CharString
			{
				c=(Token)match(input,CharString,FOLLOW_CharString_in_atomExp651); 
				value = new Expression ("literal string");
				value.setValue ((c!=null?c.getText():null));

			}
			break;
			case 4 :
				// SQL.g:155:4: c= Float
			{
				c=(Token)match(input,Float,FOLLOW_Float_in_atomExp664); 
				value = new Expression ("literal float");
				value.setValue ((c!=null?c.getText():null));

			}
			break;
			case 5 :
				// SQL.g:157:4: '(' e1= addExp ')'
			{
				match(input,20,FOLLOW_20_in_atomExp676); 
				pushFollow(FOLLOW_addExp_in_atomExp680);
				e1=addExp();

				state._fsp--;

				match(input,22,FOLLOW_22_in_atomExp682); 
				value = e1;

			}
			break;
			case 6 :
				// SQL.g:158:5: '-' atomExp
			{
				match(input,30,FOLLOW_30_in_atomExp692); 
				pushFollow(FOLLOW_atomExp_in_atomExp694);
				atomExp();

				state._fsp--;

				Expression temp = value;
				value = new Expression ("unary minus");
				value.setSubexpression (temp);

			}
			break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
		}
		return value;
	}
	// $ANTLR end "atomExp"

	// Delegated rules




	public static final BitSet FOLLOW_selectClause_in_parse31 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_fromClause_in_parse33 = new BitSet(new long[]{0x0000000000006200L});
	public static final BitSet FOLLOW_whereClause_in_parse35 = new BitSet(new long[]{0x0000000000004200L});
	public static final BitSet FOLLOW_groupingClause_in_parse37 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_parse39 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_10_in_parse46 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_parse48 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_11_in_selectClause105 = new BitSet(new long[]{0x00000000581000F0L});
	public static final BitSet FOLLOW_exprList_in_selectClause107 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_12_in_fromClause118 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_fromList_in_fromClause120 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_13_in_whereClause131 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_cnfExp_in_whereClause135 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_14_in_groupingClause151 = new BitSet(new long[]{0x0000000000008000L});
	public static final BitSet FOLLOW_15_in_groupingClause153 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_Identifier_in_groupingClause157 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_groupingClause159 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_Identifier_in_groupingClause163 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_exp_in_exprList181 = new BitSet(new long[]{0x0000000000020002L});
	public static final BitSet FOLLOW_17_in_exprList195 = new BitSet(new long[]{0x00000000581000F0L});
	public static final BitSet FOLLOW_exp_in_exprList199 = new BitSet(new long[]{0x0000000000020002L});
	public static final BitSet FOLLOW_Identifier_in_fromList220 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_18_in_fromList222 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_Identifier_in_fromList226 = new BitSet(new long[]{0x0000000000020002L});
	public static final BitSet FOLLOW_17_in_fromList240 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_Identifier_in_fromList244 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_18_in_fromList246 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_Identifier_in_fromList250 = new BitSet(new long[]{0x0000000000020002L});
	public static final BitSet FOLLOW_disjunction_in_cnfExp275 = new BitSet(new long[]{0x0000000000080002L});
	public static final BitSet FOLLOW_19_in_cnfExp285 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_disjunction_in_cnfExp289 = new BitSet(new long[]{0x0000000000080002L});
	public static final BitSet FOLLOW_20_in_disjunction311 = new BitSet(new long[]{0x000000005C1000F0L});
	public static final BitSet FOLLOW_comparison_in_disjunction315 = new BitSet(new long[]{0x0000000000600000L});
	public static final BitSet FOLLOW_21_in_disjunction324 = new BitSet(new long[]{0x000000005C1000F0L});
	public static final BitSet FOLLOW_comparison_in_disjunction328 = new BitSet(new long[]{0x0000000000600000L});
	public static final BitSet FOLLOW_22_in_disjunction337 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_addExp_in_comparison354 = new BitSet(new long[]{0x0000000003800000L});
	public static final BitSet FOLLOW_23_in_comparison363 = new BitSet(new long[]{0x00000000581000F0L});
	public static final BitSet FOLLOW_addExp_in_comparison367 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_24_in_comparison375 = new BitSet(new long[]{0x00000000581000F0L});
	public static final BitSet FOLLOW_addExp_in_comparison379 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_25_in_comparison387 = new BitSet(new long[]{0x00000000581000F0L});
	public static final BitSet FOLLOW_addExp_in_comparison391 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_26_in_comparison403 = new BitSet(new long[]{0x000000005C1000F0L});
	public static final BitSet FOLLOW_comparison_in_comparison407 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_27_in_exp424 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_exp426 = new BitSet(new long[]{0x00000000581000F0L});
	public static final BitSet FOLLOW_addExp_in_exp430 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_exp432 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_28_in_exp439 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_exp441 = new BitSet(new long[]{0x00000000581000F0L});
	public static final BitSet FOLLOW_addExp_in_exp445 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_exp447 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_addExp_in_exp456 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_multExp_in_addExp489 = new BitSet(new long[]{0x0000000060000002L});
	public static final BitSet FOLLOW_29_in_addExp506 = new BitSet(new long[]{0x00000000581000F0L});
	public static final BitSet FOLLOW_multExp_in_addExp510 = new BitSet(new long[]{0x0000000060000002L});
	public static final BitSet FOLLOW_30_in_addExp521 = new BitSet(new long[]{0x00000000581000F0L});
	public static final BitSet FOLLOW_multExp_in_addExp525 = new BitSet(new long[]{0x0000000060000002L});
	public static final BitSet FOLLOW_atomExp_in_multExp552 = new BitSet(new long[]{0x0000000180000002L});
	public static final BitSet FOLLOW_31_in_multExp569 = new BitSet(new long[]{0x00000000581000F0L});
	public static final BitSet FOLLOW_atomExp_in_multExp573 = new BitSet(new long[]{0x0000000180000002L});
	public static final BitSet FOLLOW_32_in_multExp584 = new BitSet(new long[]{0x00000000581000F0L});
	public static final BitSet FOLLOW_atomExp_in_multExp588 = new BitSet(new long[]{0x0000000180000002L});
	public static final BitSet FOLLOW_Int_in_atomExp615 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Identifier_in_atomExp631 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_16_in_atomExp633 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_Identifier_in_atomExp637 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CharString_in_atomExp651 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Float_in_atomExp664 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_20_in_atomExp676 = new BitSet(new long[]{0x00000000581000F0L});
	public static final BitSet FOLLOW_addExp_in_atomExp680 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_22_in_atomExp682 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_30_in_atomExp692 = new BitSet(new long[]{0x00000000581000F0L});
	public static final BitSet FOLLOW_atomExp_in_atomExp694 = new BitSet(new long[]{0x0000000000000002L});

}