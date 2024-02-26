package hk.ust.comp3021.utils;

import hk.ust.comp3021.expr.ASTExpr;
import hk.ust.comp3021.misc.ASTEnumOp;

import java.util.Random;
public class XMLNodeGen {

    static void setSampledLineInfo(XMLNode node) {
        node.setAttribute("lineno", "8");
        node.setAttribute("col_offset", "29");
        node.setAttribute("end_lineno", "8");
        node.setAttribute("end_col_offset", "40");
    }

    public static XMLNode generateSampleExpr() {
        Random random = new Random();
        int index = random.nextInt(ASTExpr.ExprType.values().length);
        ASTExpr.ExprType randomExprType = ASTExpr.ExprType.values()[index];

        switch (randomExprType) {
            case BoolOp:
                return generateSampleBoolOpExpr();
            case BinOp:
                return generateSampleBinOpExpr();
            case UnaryOp:
                return generateSampleUnaryOpExpr();
            case Compare:
                return generateSampleCompareExpr();
            case Call:
                return generateSampleCallExpr();
            case Constant:
                return generateSampleConstantExpr();
            case Attribute:
                return generateSampleAttributeExpr(true);
            case Subscript:
                return generateSampleSubscriptExpr();
            case Name:
                return generateSampleNameExpr();
            case List:
                return generateSampleListExpr();
            case Tuple:
                return generateSampleTupleExpr();
            default:
                throw new IllegalArgumentException("Invalid expression type: " + randomExprType);
        }
    }

    public static XMLNode generateSampleEnumOp(ASTEnumOp.ASTOperator beginOp,
                                               ASTEnumOp.ASTOperator endOp) {
        Random random = new Random();
        int startIdx = beginOp.ordinal();
        int endIdx = endOp.ordinal();

        int index = random.nextInt(endIdx - startIdx + 1) + startIdx;
        ASTEnumOp.ASTOperator randomOp = ASTEnumOp.ASTOperator.values()[index];

        switch (randomOp) {
            case Ctx_Load:
                return new XMLNode("Load");
            case Ctx_Store:
                return new XMLNode("Store");
            case Ctx_Del:
                return new XMLNode("Del");
            case OP_And:
                return new XMLNode("And");
            case OP_Or:
                return new XMLNode("Or");
            case OP_Add:
                return new XMLNode("Add");
            case OP_Sub:
                return new XMLNode("Sub");
            case OP_Mult:
                return new XMLNode("Mult");
            case OP_MatMult:
                return new XMLNode("MatMult");
            case OP_Div:
                return new XMLNode("Div");
            case OP_Mod:
                return new XMLNode("Mod");
            case OP_Pow:
                return new XMLNode("Pow");
            case OP_LShift:
                return new XMLNode("LShift");
            case OP_RShift:
                return new XMLNode("RShift");
            case OP_BitOr:
                return new XMLNode("BitOr");
            case OP_BitXor:
                return new XMLNode("BitXor");
            case OP_BitAnd:
                return new XMLNode("BitAnd");
            case OP_FloorDiv:
                return new XMLNode("FloorDiv");
            case OP_Invert:
                return new XMLNode("Invert");
            case OP_Not:
                return new XMLNode("Not");
            case OP_UAdd:
                return new XMLNode("UAdd");
            case OP_USub:
                return new XMLNode("USub");
            case OP_Eq:
                return new XMLNode("Eq");
            case OP_NotEq:
                return new XMLNode("NotEq");
            case OP_Lt:
                return new XMLNode("Lt");
            case OP_LtE:
                return new XMLNode("LtE");
            case OP_Gt:
                return new XMLNode("Gt");
            case OP_GtE:
                return new XMLNode("GtE");
            case OP_Is:
                return new XMLNode("Is");
            case OP_IsNot:
                return new XMLNode("IsNot");
            case OP_In:
                return new XMLNode("In");
            case OP_NotIn:
                return new XMLNode("NotIn");
            default:
                // Handle invalid operator
                break;
        }
        return null;
    }

    public static XMLNode generateSampleAttributeExpr(boolean recursive) {
        XMLNode node = new XMLNode("Attribute");
        node.setAttribute("attr", "next");
        setSampledLineInfo(node);

        XMLNode nameNode = new XMLNode("Name");
        nameNode.setAttribute("id", "node");
        nameNode.addChild(new XMLNode("Load"));

        setSampledLineInfo(nameNode);

        if (!recursive) {
            node.addChild(nameNode);
        } else {
            XMLNode nextNode = new XMLNode("Attribute");
            nextNode.setAttribute("attr", "abc");
            setSampledLineInfo(node);
            nextNode.addChild(nameNode);
            nextNode.addChild(new XMLNode("Load"));
            node.addChild(nextNode);
        }
        node.addChild(new XMLNode("Load"));
        return node;
    }

    public static XMLNode generateSampleBinOpExpr() {
        XMLNode node = new XMLNode("BinOp");
        setSampledLineInfo(node);

        node.addChild(generateSampleNameExpr());

        node.addChild(generateSampleEnumOp(
                ASTEnumOp.ASTOperator.OP_Add,
                ASTEnumOp.ASTOperator.OP_FloorDiv));


        node.addChild(generateSampleConstantExpr());
        return node;
    }

    public static XMLNode generateSampleBoolOpExpr() {
        XMLNode node = new XMLNode("BoolOp");
        setSampledLineInfo(node);

        node.addChild(generateSampleEnumOp(
                ASTEnumOp.ASTOperator.OP_And,
                ASTEnumOp.ASTOperator.OP_Or));

        XMLNode valuesNode = new XMLNode("values");

        valuesNode.addChild(generateSampleAttributeExpr(true));
        valuesNode.addChild(generateSampleCompareExpr());

        node.addChild(valuesNode);
        return node;
    }

    public static XMLNode generateSampleCallExpr() {
        XMLNode node = new XMLNode("Call");
        setSampledLineInfo(node);

        node.addChild(generateSampleAttributeExpr(false));

        XMLNode args = new XMLNode("args");
        args.addChild(generateSampleNameExpr());

        node.addChild(args);

        node.addChild(new XMLNode("keywords"));
        return node;
    }

    public static XMLNode generateSampleCompareExpr() {
        XMLNode node = new XMLNode("Compare");
        setSampledLineInfo(node);

        node.addChild(generateSampleNameExpr());

        XMLNode opsNode = new XMLNode("ops");
        opsNode.addChild(generateSampleEnumOp(
                ASTEnumOp.ASTOperator.OP_Eq,
                ASTEnumOp.ASTOperator.OP_NotIn));
        node.addChild(opsNode);

        XMLNode compsNode = new XMLNode("comparators");
        compsNode.addChild(generateSampleBinOpExpr());
        node.addChild(compsNode);
        return node;
    }

    public static XMLNode generateSampleConstantExpr() {
        XMLNode constant = new XMLNode("Constant");
        constant.setAttribute("value", "1");
        constant.setAttribute("kind", "None");
        setSampledLineInfo(constant);
        return constant;
    }

    public static XMLNode generateSampleListExpr() {
        XMLNode node = new XMLNode("List");
        setSampledLineInfo(node);

        XMLNode eltsNode = new XMLNode("elts");
        eltsNode.addChild(generateSampleNameExpr());
        eltsNode.addChild(generateSampleNameExpr());

        node.addChild(eltsNode);

        node.addChild(new XMLNode("Load"));

        return node;
    }


    // Sample function to generate a sample NameExpr
    public static XMLNode generateSampleNameExpr() {
        XMLNode nameNode = new XMLNode("Name");
        nameNode.setAttribute("id", "node");
        nameNode.addChild(new XMLNode("Load"));

        setSampledLineInfo(nameNode);
        return nameNode;
    }


    // Sample function to generate a sample SubscriptExpr
    public static XMLNode generateSampleSubscriptExpr() {
        XMLNode node = new XMLNode("Subscript");
        setSampledLineInfo(node);

        node.addChild(generateSampleNameExpr());
        node.addChild(generateSampleNameExpr());
        node.addChild(new XMLNode("Load"));

        return node;
    }

    // Sample function to generate a sample TupleExpr
    public static XMLNode generateSampleTupleExpr() {
        XMLNode node = new XMLNode("Tuple");
        setSampledLineInfo(node);

        XMLNode eltsNode = new XMLNode("elts");
        eltsNode.addChild(generateSampleNameExpr());
        eltsNode.addChild(generateSampleNameExpr());

        node.addChild(eltsNode);

        node.addChild(new XMLNode("Store"));

        return node;
    }

    // Sample function to generate a sample UnaryOpExpr
    public static XMLNode generateSampleUnaryOpExpr() {
        XMLNode node = new XMLNode("UnaryOp");
        setSampledLineInfo(node);

        node.addChild(generateSampleEnumOp(
                ASTEnumOp.ASTOperator.OP_Invert,
                ASTEnumOp.ASTOperator.OP_USub));

        node.addChild(generateSampleNameExpr());

        return node;
    }

    public static XMLNode generateSampleKeyWord() {
        XMLNode node = new XMLNode("keyword");
        setSampledLineInfo(node);

        node.setAttribute("arg", "key");
        node.addChild(generateSampleNameExpr());

        return node;
    }
}