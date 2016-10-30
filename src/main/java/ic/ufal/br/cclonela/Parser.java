package ic.ufal.br.cclonela;

public class Parser {
	protected Analyzer lexicalAnalyzer;
	private Token token;
	// protected LinkedList<Token> ll;

	public Parser(Analyzer analyzer) {
		this.lexicalAnalyzer = analyzer;
		// this.ll = ll;
	}

	void nextToken() {
		token = lexicalAnalyzer.nextToken();
	}

	void start() {
		nextToken();
		System.out.println(token.getValue());
		program();
	}

	void error(int id) {
		System.out.println("erro" + id);
		System.exit(id);
	}

	void program() {
		// return this.mainFunc();
		System.out.println("PROGRAM -> FUNCTIONS");
		functions();

	}

	void functions() {
		System.out.println("FUNCTIONS-> RETURNTYPE FUNCTIONS2");
		returnType();
		functions2();

	}

	void functions2() {
		if (token.categ == Categories.id) {
			System.out.println("FUNCTIONS2 -> 'id' (" + token.getValue() + ") PARAM SCOPE FUNCTIONS");
			nextToken();
			param();
			scope();
			functions();
		} else if (token.categ == Categories.prMain) {
			System.out.println("FUNCTIONS2 -> 'prMain' PARAM SCOPE");
			nextToken();
			param();
			scope();
		} else {
			error(0);
		}
	}

	void returnType() {

		if (token.categ == Categories.prVoid) {
			System.out.println("RETURNTYPE -> void");
			nextToken();
		} else {
			System.out.println("RETURNTYPE -> TYPE TYPE2");
			type();
			type2();
		}

	}

	void param() {
		if (token.categ == Categories.abPar) {
			System.out.println("PARAM -> ‘abPar’ PARAM2");
			nextToken();
			param2();
		}
	}

	void param2() {

		if (token.categ == Categories.prVoid) {
			System.out.print("PARAM2 -> ‘prVoid’(void) ");
			nextToken();
		} else {
			System.out.print("PARAM2 ->  PARAMLIST ");
			paramList();
		}
		if (token.categ == Categories.fcPar) {
			System.out.println("‘fcPar'(')')");
			nextToken();

		} else {
			error(1);
		}

	}

	void exp() {
		System.out.println("EXP -> EXP2 EXPAUX");
		exp2();
		expAux();
	}

	void expAux() {
		if (token.categ == Categories.opConj) {
			System.out.println("EXPAUX  -> 'opConj'(&&) EXP2 EXPAUX ");
			nextToken();
			exp2();
			expAux();
		} else {
			System.out.println("EXPAUX  -> epsilon");
			return;
		}
	}

	void exp2() {
		System.out.println("EXP2 -> EXPCOMP EXP2AUX");
		expComp();
		exp2Aux();
	}

	void exp2Aux() {

		if (token.categ == Categories.opDisj) {
			System.out.println("EXP2AUX -> 'opDisj'(||) EXPCOMP EXP2AUX ");
			;
			nextToken();
			expComp();
			exp2Aux();
		} else {
			System.out.println("EXP2AUX -> epsilon");
			return;
		}

	}

	void expComp() {
		System.out.println("EXPCOMP -> RELEXP EXPCOMP2");
		relExp();
		expComp2();
	}

	void expComp2() {

		if (token.categ == Categories.opIg || token.categ == Categories.opDif) {
			System.out.println("EXPCOMP2 -> EXPCOMPAUX EXPCOMP2 ");
			expCompAux();
			expComp2();
		} else {
			System.out.println("EXPCOMP2 -> epsilon");
			return;
		}

	}

	void expCompAux() {

		if (token.categ == Categories.opIg) {
			System.out.println("EXPCOMPAUX -> 'opIg'(==) RELEXP ");
			nextToken();
			relExp();
		} else if (token.categ == Categories.opDif) {
			System.out.println("EXPCOMPAUX -> 'opDif'(!=) RELEXP");
			nextToken();
			relExp();
		} else
			error(2);
	}

	void relExp() {
		System.out.println("RELEXP -> NUMEXP RELEXP2");
		numExp();
		relExp2();
	}

	void relExp2() {

		if (token.categ == Categories.opMai || token.categ == Categories.opMa || token.categ == Categories.opMei
				|| token.categ == Categories.opMe) {
			System.out.println("RELEXP2 -> RELEXPAUX  RELEXP2");
			relExpAux();
			relExp2();

		} else {
			System.out.println("RELEXP2 -> epsilon");
			return;
		}

	}

	void relExpAux() {

		if (token.categ == Categories.opMai) {
			System.out.println("RELEXPAUX -> 'opMai'(>=) NUMEXP");
			nextToken();
			numExp();

		} else if (token.categ == Categories.opMa) {
			System.out.println("RELEXPAUX -> 'opMa'(>) NUMEXP");
			nextToken();
			numExp();

		} else if (token.categ == Categories.opMei) {
			System.out.println("RELEXPAUX -> 'opMei'(<=) NUMEXP");
			nextToken();
			numExp();

		} else if (token.categ == Categories.opMe) {
			System.out.println("RELEXPAUX -> 'opMe'(<) NUMEXP");
			nextToken();
			numExp();

		} else
			error(3);
	}

	void numExp() {
		System.out.println("NUMEXP -> NUMEXP2 NUMEXPAUX2");
		numExp2();
		numExpAux2();
	}

	void numExpAux2() {
		System.out.println("numexpaux2");
		System.out.println(token.categ);
		if (token.categ == Categories.opAd || token.categ == Categories.opSub) {
			System.out.println("NUMEXPAUX2 - > NUMEXPAUX  NUMEXPAUX2");
			numExpAux();
			numExpAux2();
		} else {
			System.out.println("NUMEXPAUX2 - > epsilon");
			return;
		}

	}

	void numExpAux() {

		if (token.categ == Categories.opAd) {
			System.out.println("NUMEXPAUX -> ‘opAd’(+) NUMEXP2 ");
			nextToken();
			numExp2();
		} else if (token.categ == Categories.opSub) {
			System.out.println("NUMEXPAUX -> ‘opSub’(-) NUMEXP2 ");
			nextToken();
			numExp2();
		} else
			error(4);
	}

	void numExp2() {
		System.out.println("NUMEXP2 -> NUMEXP3 NUMEXP2AUX2");
		numExp3();
		numExp2Aux2();
	}

	void numExp2Aux2() {

		if (token.categ == Categories.opMult || token.categ == Categories.opMod || token.categ == Categories.opDiv) {
			System.out.println("NUMEXP2AUX2 -> NUMEXP2AUX NUMEXP2AUX2");
			numExp2Aux();
			numExp2Aux2();
		} else {
			System.out.println("NUMEXP2AUX2 -> epsilon");
			return;
		}

	}

	void numExp2Aux() {

		if (token.categ == Categories.opMult) {
			System.out.println("NUMEXP2AUX -> ‘opMult’(*) NUMEXP3");
			nextToken();
			numExp3();
		} else if (token.categ == Categories.opMod) {
			System.out.println("NUMEXP2AUX -> ‘opMod’(%) NUMEXP3");
			nextToken();
			numExp3();
		} else if (token.categ == Categories.opDiv) {
			System.out.println("NUMEXP2AUX -> ‘opDiv’(/) NUMEXP3");
			nextToken();
			numExp3();
		} else {
			error(5);
		}

	}

	void numExp3() {
		System.out.print("NUMEXP3 -> ");
		if (token.categ == Categories.opNeg) {
			System.out.print("‘opNeg’(!) ");
			nextToken();
		} else if (token.categ == Categories.opInc) {
			System.out.print("‘opInc’(" + token.getValue() + ") ");
			nextToken();
		} else if (token.categ == Categories.opSub) {
			System.out.print("‘opSub’(-) ");
			nextToken();
		}

		System.out.println("NUMEXP4 -> ");
		numExp4();

	}

	void numExp4() {

		if (token.categ == Categories.abPar) {
			nextToken();
			exp();
			if (token.categ == Categories.fcPar) {
				System.out.println("NUMEXP4 -> ‘abPar’(() EXP  ‘fcPar’())");
				nextToken();
			} else
				error(6);
		} else if (token.categ == Categories.id) {
			System.out.println("NUMEXP4 -> NAME");
			name();
		} else if (token.categ == Categories.cteInt) {
			System.out.println("NUMEXP4 -> 'cteInt'(" + token.getValue() + ")");
			nextToken();
		} else if (token.categ == Categories.cteFloat) {
			System.out.println("NUMEXP4 -> 'cteFloat'(" + token.getValue() + ")");
			nextToken();
		} else if (token.categ == Categories.prTrue) {
			System.out.println("NUMEXP4 -> 'prTrue'(true)");
			nextToken();
		} else if (token.categ == Categories.prFalse) {
			System.out.println("NUMEXP4 -> 'cteInt'(false)");
			nextToken();
		} else
			error(7);
	}

	void name() {

		if (token.categ == Categories.id) {
			System.out.println("NAME -> ‘id’ NAME2");
			nextToken();
			name2();
		} else
			error(8);
	}

	void name2() {
		if (token.categ == Categories.abPar) {
			System.out.println("NAME2 -> FUNCCALLCMD");
			funccallCmd();
		} else if (token.categ == Categories.abCol) {
			nextToken();
			exp();
			if (token.categ == Categories.fcCol) {
				System.out.println("NAME2 -> ‘abCol’ EXP ‘fcCol’ ");
				nextToken();
			} else
				error(45);
		} else {
			System.out.println("NAME2 -> epsilon");
			return;
		}

	}

	void paramList() {

		type();
		type2();

		if (token.categ == Categories.id) {
			System.out.println("PARAMLIST -> TYPE TYPE2 ‘id’(" + token.getValue() + ") PARAMLIST2");
			nextToken();
			paramList2();
		} else
			error(9);

	}

	void paramList2() {

		if (token.categ == Categories.sep) {
			System.out.println("PARAMLIST2 -> ‘sep’(,) PARAMLIST");
			nextToken();
			paramList();
		} else {
			System.out.println("PARAMLIST2 -> epsilon");
			return;
		}

	}

	void type() {
		if (token.categ == Categories.prBool) {
			System.out.println("TYPE -> 'prBool'(bool)");
			nextToken();

		} else if (token.categ == Categories.prChar) {
			System.out.println("TYPE -> 'prChar'(char)");

			nextToken();

		} else if (token.categ == Categories.prInt) {
			System.out.println("TYPE -> 'prInt'(int)");
			nextToken();

		} else if (token.categ == Categories.prFloat) {
			System.out.println("TYPE -> 'prFloat'(float)");
			nextToken();

		} else {
			System.out.println(token.toString());
			error(10);
		}
	}

	void type2() {

		if (token.categ == Categories.abCol) {
			nextToken();
			if (token.categ == Categories.fcCol) {
				System.out.println("TYPE2 -> ‘abCol’([) ‘fcCol’(]) ");
				nextToken();
			} else
				error(11);
		} else {
			System.out.println("TYPE2 -> epsilon");
			return;
		}

	}

	void scope() {

		if (token.categ == Categories.abCh) {
			System.out.println("SCOPE -> ‘abCh’({) SCOPE2");
			nextToken();
			scope2();
		} else
			error(13);
	}

	void scope2() {
		
		cmd();
		if (token.categ == Categories.fcCh) {
			nextToken();
		}else error(46);

	}

	void cmd() {
		
		if (token.categ == Categories.prBool || token.categ == Categories.prChar || token.categ == Categories.prInt
				|| token.categ == Categories.prFloat) {
			declarationCmd();
			cmd();
		} else if (token.categ == Categories.id) {
			nextToken();
			System.out.println(token.getValue());
			cmd2();
			cmd();
		} else if (token.categ == Categories.prIf) {
			ifelseCmd();
			cmd();
		} else if (token.categ == Categories.prWhile) {
			whileCmd();
			cmd();
		} else if (token.categ == Categories.prFor) {
			forCmd();
			cmd();
		} else if (token.categ == Categories.prRet) {
			returnCmd();
			cmd();
		} else
			return;
	}

	void cmd2() {
		System.out.println("cmd2");
		System.out.println(token.categ);
		System.out.println(token.getValue());
		if (token.categ == Categories.abPar) {
			funccallCmd();
		} else if (token.categ == Categories.abCol) {
			nextToken();
			exp();

			if (token.categ == Categories.fcCol) {
				nextToken();
				attrCmd();
			} else
				error(15);

		} else if (token.categ == Categories.opAtr) {
			attrCmd();
		} else
			error(16);

		if (token.categ == Categories.term) {
			nextToken();
		} else {
			System.out.println(token.toString());
			error(40);
		}
	}

	void declarationCmd() {
		System.out.println("declarationcmd");
		System.out.println(token.categ);
		type();
		declarationCmd2();
		if (token.categ == Categories.term) {
			System.out.println("aqui");
			nextToken();
		} else
			error(39);
	}

	void declarationCmd2() {
		System.out.println("declarationcmd2");
		System.out.println(token.categ);
		if (token.categ == Categories.id) {
			nextToken();
			declarationCmd3();
		} else if (token.categ == Categories.abCol)
			arrayDec();
		else
			error(17);
	}

	void declarationCmd3() {
		System.out.println("declaratoincmd3");
		System.out.println(token.categ);
		if (token.categ == Categories.opAtr) {
			// atribuição é operador
			attrCmd();
		} else
			return;
	}

	void arrayDec() {
		System.out.println("arraydec");
		System.out.println(token.categ);
		if (token.categ == Categories.abCol) {
			nextToken();
			exp();
			if (token.categ == Categories.fcCol)
				nextToken();
			else
				error(18);

			if (token.categ == Categories.id)
				nextToken();
			else
				error(19);

			arrayDec2();
		} else
			error(20);
	}

	void arrayDec2() {
		System.out.println("arraydec2");
		System.out.println(token.categ);
		if (token.categ == Categories.opAtr)
			arrayAttrCmd();
		else
			return;
	}

	void attrCmd() {
		System.out.println("attrcmd");
		System.out.println(token.categ);
		if (token.categ == Categories.opAtr) {
			nextToken();
			exp();
		} else
			error(21);
	}

	void arrayAttrCmd() {
		System.out.println("arrayattrcmd");
		System.out.println(token.categ);
		if (token.categ == Categories.opAtr) {
			nextToken();
			arrayInit();
		} else
			error(22);
	}

	void arrayInit() {
		System.out.println("arrayinit");
		System.out.println(token.categ);
		if (token.categ == Categories.abCh) {
			nextToken();
			elem();
			if (token.categ == Categories.fcCh)
				nextToken();
			else
				error(23);
		} else
			error(24);
	}

	void elem() {
		System.out.println("elem");
		System.out.println(token.categ);
		exp();
		elem2();
	}

	void elem2() {
		System.out.println("elem2");
		System.out.println(token.categ);
		if (token.categ == Categories.sep) {
			nextToken();
			elem();
		} else
			return;
	}

	void constant() {
		System.out.println("constant");
		System.out.println(token.categ);
		if (token.categ == Categories.cteCh || token.categ == Categories.cteFloat || token.categ == Categories.cteInt
				|| token.categ == Categories.cteStr) {
			nextToken();
		} else
			error(25);
	}

	void funccallCmd() {
		System.out.println("funcallcmd");
		System.out.println(token.categ);
		if (token.categ == Categories.abPar) {
			nextToken();
			funccallCmd2();
		} else
			error(26);
	}

	void funccallCmd2() {
		if (token.categ != Categories.fcPar)
			funccallParamList();

		if (token.categ == Categories.fcPar)
			nextToken();
		else
			error(27);
	}

	void funccallParamList() {
		System.out.println("funccallParamlist");
		System.out.println(token.categ);
		exp();
		funccallParamList2();
	}

	void funccallParamList2() {
		System.out.println("funccallParamlist2");
		System.out.println(token.categ);
		if (token.categ == Categories.sep) {
			nextToken();
			funccallParamList();
		} else
			return;
	}

	/*
	 * void funccallParam(){ if(token.categ == Categories.id) id(); else exp();
	 * }
	 */

	void ifelseCmd() {
		System.out.println("ifelsecmd");
		System.out.println(token.categ);
		ifCmd();
		elseifCmd();

	}

	void ifCmd() {
		System.out.println("ifcmd");
		System.out.println(token.categ);
		if (token.categ == Categories.prIf) {
			nextToken();
			if (token.categ == Categories.abPar) {
				nextToken();
				exp();
				if (token.categ == Categories.fcPar) {
					nextToken();
					scope();
				} else
					error(28);
			} else
				error(29);
		} else
			error(30);
	}

	void elseifCmd() {
		System.out.println("elseifcmd");
		System.out.println(token.categ);
		if (token.categ == Categories.prElse) {
			nextToken();
			elseifCmd2();
		} else
			return;
	}

	void elseifCmd2() {
		System.out.println("elseifcmd2");
		System.out.println(token.categ);
		if (token.categ == Categories.prIf) {
			ifelseCmd();
			// ifCmd();
			// elseifCmd();
		} else if (token.categ == Categories.abCh)
			elseCmd();
		else
			error(32);
	}

	void elseCmd() {
		System.out.println("elsecmd");
		System.out.println(token.categ);
		scope();
	}

	void whileCmd() {
		System.out.println("whilecmd");
		System.out.println(token.categ);
		if (token.categ == Categories.prWhile) {
			nextToken();
			if (token.categ == Categories.abPar) {
				nextToken();
				exp();
				if (token.categ == Categories.fcPar) {
					nextToken();
					scope();
				}

				else
					error(33);
			} else
				error(34);
		} else
			error(35);
	}

	void forCmd() {
		System.out.println("forcmd");
		System.out.println(token.categ);
		if (token.categ == Categories.prFor) {
			nextToken();
			if (token.categ == Categories.abPar) {
				nextToken();
				if (token.categ == Categories.id)
					nextToken();
				else
					error(46);
				attrCmd();
				if (token.categ == Categories.term)
					nextToken();
				else
					error(42);
				exp();
				if (token.categ == Categories.term)
					nextToken();
				else {
					System.out.println(token.toString());
					error(43);
				}

				if (token.categ == Categories.cteInt)
					nextToken();
				else
					error(44);
				if (token.categ == Categories.fcPar)
					nextToken();
				else
					error(45);
				scope();
			} else
				error(41);
		} else
			error(36);
	}

	void returnCmd() {
		System.out.println("returncmd");
		System.out.println(token.categ);
		if (token.categ == Categories.prRet) {
			nextToken();
			exp();
			if (token.categ == Categories.term) {
				nextToken();

			} else {
				error(37);
			}

		} else
			error(38);
	}

}
