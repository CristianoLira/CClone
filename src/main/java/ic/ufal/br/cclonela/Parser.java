package ic.ufal.br.cclonela;

import java.util.LinkedList;

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
		System.out.println("program");
		System.out.println(token.categ);
		functions();

	}

	void functions() {
		System.out.println("functions");
		System.out.println(token.categ);
		// System.out.println(token.categ);
		returnType();
		functions2();

	}

	void functions2() {
		System.out.println("functions2");
		System.out.println(token.categ);
		if (token.categ == Categories.id) {
			nextToken();
			param();
			scope();
			functions();
		} else if (token.categ == Categories.prMain) {
			nextToken();
			param();
			scope();
		} else {
			error(0);
		}
	}

	void returnType() {
		System.out.println("returntype");
		System.out.println(token.categ);
		if (token.categ == Categories.prVoid) {
			nextToken();
		} else {
			type();
			type2();
		}

	}

	void param() {
		System.out.println("param");
		System.out.println(token.categ);
		if (token.categ == Categories.abPar) {
			nextToken();
			param2();
		}
	}

	void param2() {
		System.out.println("param2");
		System.out.println(token.categ);
		if (token.categ == Categories.prVoid) {
			nextToken();
		} else {
			paramList();
		}
		if (token.categ == Categories.fcPar) {
			nextToken();

		} else {
			error(1);
		}

	}

	void exp() {
		System.out.println("exp");
		System.out.println(token.categ);
		exp2();
		expAux();
	}

	void expAux() {
		System.out.println("expaux");
		System.out.println(token.categ);
		if (token.categ == Categories.opConj) {
			nextToken();
			exp2();
			expAux();
		} else
			return;
	}

	void exp2() {
		System.out.println("exp2");
		System.out.println(token.categ);
		expComp();
		exp2Aux();
	}

	void exp2Aux() {
		System.out.println("exp2aux");
		System.out.println(token.categ);
		if (token.categ == Categories.opDisj) {
			nextToken();
			expComp();
			exp2Aux();
		} else
			return;
	}

	void expComp() {
		System.out.println("expcomp");
		System.out.println(token.categ);
		relExp();
		expComp2();
	}

	void expComp2() {
		System.out.println("expcomp2");
		System.out.println(token.categ);
		if (token.categ == Categories.opIg || token.categ == Categories.opDif) {
			expCompAux();
			expComp2();
		} else
			return;
	}

	void expCompAux() {
		System.out.println("expcompaux");
		System.out.println(token.categ);
		if (token.categ == Categories.opIg || token.categ == Categories.opDif) {
			nextToken();
			relExp();
		} else
			error(2);
	}

	void relExp() {
		System.out.println("relexp");
		System.out.println(token.categ);
		numExp();
		relExp2();
	}

	void relExp2() {
		System.out.println("relexp2");
		System.out.println(token.categ);
		if (token.categ == Categories.opMai || token.categ == Categories.opMa || token.categ == Categories.opMei
				|| token.categ == Categories.opMe) {
			relExpAux();
			relExp2();

		} else
			return;
	}

	void relExpAux() {
		System.out.println("relexpaux");
		System.out.println(token.categ);
		if (token.categ == Categories.opMai || token.categ == Categories.opMa || token.categ == Categories.opMei
				|| token.categ == Categories.opMe) {
			nextToken();
			numExp();

		} else
			error(3);
	}

	void numExp() {
		System.out.println("numexp");
		System.out.println(token.categ);
		numExp2();
		numExpAux2();
	}

	void numExpAux2() {
		System.out.println("numexpaux2");
		System.out.println(token.categ);
		if (token.categ == Categories.opAd || token.categ == Categories.opSub) {
			numExpAux();
			numExpAux2();
		} else
			return;
	}

	void numExpAux() {
		System.out.println("numexpaux");
		System.out.println(token.categ);
		if (token.categ == Categories.opAd || token.categ == Categories.opSub) {
			nextToken();
			numExp2();
		} else
			error(4);
	}

	void numExp2() {
		System.out.println("numexp2");
		System.out.println(token.categ);
		numExp3();
		numExp2Aux2();
	}

	void numExp2Aux2() {
		System.out.println("numexp2aux2");
		System.out.println(token.categ);
		if (token.categ == Categories.opMult || token.categ == Categories.opMod || token.categ == Categories.opDiv) {
			nextToken();
			numExp2Aux();
			numExp2Aux2();
		} else
			return;
	}

	void numExp2Aux() {
		System.out.println("numexp2aux");
		System.out.println(token.categ);
		if (token.categ == Categories.opMult || token.categ == Categories.opMod || token.categ == Categories.opDiv) {
			nextToken();
			numExp3();
		} else
			error(5);
	}

	void numExp3() {
		System.out.println("numexp3");
		System.out.println(token.categ);
		if (token.categ == Categories.opNeg || token.categ == Categories.opInc || token.categ == Categories.opSub) {
			nextToken();
		}

		numExp4();

	}

	void numExp4() {
		System.out.println("numexp4");
		System.out.println(token.categ);
		if (token.categ == Categories.abPar) {
			nextToken();
			exp();
			if (token.categ == Categories.fcPar) {
				nextToken();
			} else
				error(6);
		} else if (token.categ == Categories.id) {
			name();
		} else if (token.categ == Categories.cteInt || token.categ == Categories.cteFloat
				|| token.categ == Categories.prTrue || token.categ == Categories.prFalse) {
			nextToken();
		} else
			error(7);
	}

	void name() {
		System.out.println("name");
		System.out.println(token.categ);
		if (token.categ == Categories.id) {
			nextToken();
			name2();
		} else
			error(8);
	}

	void name2() {
		System.out.println("name2");
		System.out.println(token.categ);
		if (token.categ == Categories.abPar) {
			funccallCmd();
		} else
			return;
	}

	void paramList() {
		System.out.println("paramlist");
		System.out.println(token.categ);
		type();
		type2();

		if (token.categ == Categories.id) {
			nextToken();
			paramList2();
		} else
			error(9);

	}

	void paramList2() {
		System.out.println("paramlist2");
		System.out.println(token.categ);
		if (token.categ == Categories.sep) {
			nextToken();
			paramList();
		} else
			return;
	}

	void type() {
		System.out.println("type");
		System.out.println(token.categ);
		System.out.println(token.getValue());
		if (token.categ == Categories.prBool || token.categ == Categories.prChar || token.categ == Categories.prInt
				|| token.categ == Categories.prFloat) {
			nextToken();

		} else
			error(10);
	}

	void type2() {
		System.out.println("type2");
		System.out.println(token.categ);
		if (token.categ == Categories.abCol) {
			nextToken();
			if (token.categ == Categories.fcCol)
				nextToken();
			else
				error(11);
		} else
			return;
	}

	void scope() {
		System.out.println("scope");
		System.out.println(token.toString());
		
		if (token.categ == Categories.abCh) {
			nextToken();
			cmd();
			if (token.categ == Categories.fcCh)
				nextToken();
			else
				error(12);
		} else
			error(13);
	}

	void scope2() {
		System.out.println("scope2");
		System.out.println(token.categ);
		if (token.categ == Categories.fcCh)
			nextToken();
		else {
			cmd();
			scope2();
		}
	}

	void cmd() {
		System.out.println("cmd");
		System.out.println(token.categ);
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
		if(token.categ == Categories.term){
			nextToken();
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
			funccallParamList();
			if (token.categ == Categories.fcPar)
				nextToken();
			else
				error(26);
		} else
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
		elseCmd();

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
			error(31);
	}

	void elseifCmd2() {
		System.out.println("elseifcmd2");
		System.out.println(token.categ);
		if (token.categ == Categories.prIf) {
			ifCmd();
			elseifCmd();
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
				if (token.categ == Categories.fcPar)
					scope();
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
