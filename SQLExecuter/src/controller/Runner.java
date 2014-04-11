package controller;


import java.util.*;
import java.io.*;

import execute.*;

/**
 * TA Hours:
 * Jacob: DH 3062 Tue 2:30-3:30 (jacobgao)
 * Shangyu: DH 3001 Wed 2:30-3:30 (sl45)
 * Xian: DH 3075 Mon 1-2 (xf2)
 * @author Caidie
 *
 */
class Runner {
 
  
  static public void run () {

	System.out.println(System.getProperty("user.dir"));
    long startTime = System.currentTimeMillis();  
    System.out.println ("first running a selection...");
    //DoSelection ();
    System.out.println ("now running a join...");
    DoJoin ();
    System.out.println ("now running a group by...");
    //DoGroupBy ();
    long endTime = System.currentTimeMillis();
    System.out.println("The run took " + (endTime - startTime) + " milliseconds");
  }
  
  
    
  /*****************************************/
  // This code shows how to run a group by //
  /*****************************************/
  
  private static void DoGroupBy () {
      
    ArrayList <Attribute> inAtts = new ArrayList <Attribute> ();
    inAtts.add (new Attribute ("Int", "o_orderkey"));
    inAtts.add (new Attribute ("Int", "o_custkey"));
    inAtts.add (new Attribute ("Str", "o_orderstatus"));
    inAtts.add (new Attribute ("Float", "o_totalprice"));
    inAtts.add (new Attribute ("Str", "o_orderdate"));
    inAtts.add (new Attribute ("Str", "o_orderpriority"));
    inAtts.add (new Attribute ("Str", "o_clerk"));
    inAtts.add (new Attribute ("Int", "o_shippriority"));
    inAtts.add (new Attribute ("Str", "o_comment"));
    
    ArrayList <Attribute> outAtts = new ArrayList <Attribute> ();
    outAtts.add (new Attribute ("Str", "att1"));
    outAtts.add (new Attribute ("Str", "att2"));
    outAtts.add (new Attribute ("Float", "att3"));
    outAtts.add (new Attribute ("Int", "att4"));
    
    ArrayList <String> groupingAtts = new ArrayList <String> ();
    groupingAtts.add ("o_orderdate");
    groupingAtts.add ("o_orderstatus");
    
    HashMap <String, AggFunc> myAggs = new HashMap <String, AggFunc>  ();
    myAggs.put ("att1", new AggFunc ("none", "Str(\"status: \") + o_orderstatus"));
    myAggs.put ("att2", new AggFunc ("none", "Str(\"date: \") + o_orderdate"));
    myAggs.put ("att3", new AggFunc ("avg", "o_totalprice * Int (100)"));
    myAggs.put ("att4", new AggFunc ("sum", "Int (1)"));
    
    // run the selection operation
    try {
      Grouping foo = new Grouping (inAtts, outAtts, groupingAtts, myAggs, "orders.tbl", "out.tbl", "g++", "cppDir/"); 
    } catch (Exception e) {
      throw new RuntimeException (e);
    }
  }
  
  /******************************************/
  // This code shows how to run a selection //
  /******************************************/
  
  private static void DoSelection () {
      
    ArrayList <Attribute> inAtts = new ArrayList <Attribute> ();
    inAtts.add (new Attribute ("Int", "o_orderkey"));
    inAtts.add (new Attribute ("Int", "o_custkey"));
    inAtts.add (new Attribute ("Str", "o_orderstatus"));
    inAtts.add (new Attribute ("Float", "o_totalprice"));
    inAtts.add (new Attribute ("Str", "o_orderdate"));
    inAtts.add (new Attribute ("Str", "o_orderpriority"));
    inAtts.add (new Attribute ("Str", "o_clerk"));
    inAtts.add (new Attribute ("Int", "o_shippriority"));
    inAtts.add (new Attribute ("Str", "o_comment"));
    
    ArrayList <Attribute> outAtts = new ArrayList <Attribute> ();
    outAtts.add (new Attribute ("Int", "att1"));
    outAtts.add (new Attribute ("Float", "att2"));
    outAtts.add (new Attribute ("Str", "att3"));
    outAtts.add (new Attribute ("Int", "att4"));
    
    String selection = "o_orderdate > Str (\"1996-12-19\") && o_custkey < Int (100)";
    
    HashMap <String, String> exprs = new HashMap <String, String> ();
    exprs.put ("att1", "o_orderkey");
    exprs.put ("att2", "(o_totalprice * Float (1.5)) + Int (1)");
    exprs.put ("att3", "o_orderdate + Str (\" this is my string\")");
    exprs.put ("att4", "o_custkey");
    
    // run the selection operation
    try {
      Selection foo = new Selection (inAtts, outAtts, selection, exprs, "orders.tbl", "out.tbl", "g++", "cppDir/"); 
    } catch (Exception e) {
      throw new RuntimeException (e);
    }
  }
  
  /*************************************/
  // This code shows how to run a join //
  /*************************************/
    
  private static void DoJoin () {
      
    ArrayList <Attribute> inAttsLeft = new ArrayList <Attribute> ();
    inAttsLeft.add (new Attribute ("Int", "p_partkey"));
    inAttsLeft.add (new Attribute ("Int", "o_custkey"));
    inAttsLeft.add (new Attribute ("Int", "l_partkey"));
    inAttsLeft.add (new Attribute ("Int", "o_orderkey"));
    inAttsLeft.add (new Attribute ("Int", "c_nationkey"));
    inAttsLeft.add (new Attribute ("Str", "o_orderdate"));
    inAttsLeft.add (new Attribute ("Float", "l_extendedprice"));
    inAttsLeft.add (new Attribute ("Int", "l_orderkey"));
    inAttsLeft.add (new Attribute ("Int", "c_custkey"));
    inAttsLeft.add (new Attribute ("Float", "l_discount"));
    inAttsLeft.add (new Attribute ("Int", "s_suppkey"));
    inAttsLeft.add (new Attribute ("Int", "s_nationkey"));
    inAttsLeft.add (new Attribute ("Int", "l_suppkey"));
    inAttsLeft.add (new Attribute ("Int", "r_regionkey"));
    inAttsLeft.add (new Attribute ("Int", "n_nationkey"));
    inAttsLeft.add (new Attribute ("Int", "n_regionkey"));
    
    ArrayList <Attribute> inAttsRight = new ArrayList <Attribute> ();
    inAttsRight.add (new Attribute ("Int", "r_regionkey"));
    
    ArrayList <Attribute> outAtts = new ArrayList <Attribute> ();
    outAtts.add (new Attribute ("Int", "p_partkeyoutLeft"));
    outAtts.add (new Attribute ("Int", "o_custkeyoutLeft"));
    outAtts.add (new Attribute ("Int", "l_partkeyoutLeft"));
    outAtts.add (new Attribute ("Int", "o_orderkeyoutLeft"));
    outAtts.add (new Attribute ("Int", "c_nationkeyoutLeft"));
    outAtts.add (new Attribute ("Str", "o_orderdateoutLeft"));
    outAtts.add (new Attribute ("Float", "l_extendedpriceoutLeft"));
    outAtts.add (new Attribute ("Int", "l_orderkeyoutLeft"));
    outAtts.add (new Attribute ("Int", "c_custkeyoutLeft"));
    outAtts.add (new Attribute ("Float", "l_discountoutLeft"));
    outAtts.add (new Attribute ("Int", "s_suppkeyoutLeft"));
    outAtts.add (new Attribute ("Int", "n_nationkeyoutLeft"));
    outAtts.add (new Attribute ("Int", "l_suppkeyoutLeft"));
    outAtts.add (new Attribute ("Int", "r_regionkeyoutLeft"));
    outAtts.add (new Attribute ("Int", "n_regionkeyoutLeft"));
    outAtts.add (new Attribute ("Int", "r_regionkeyoutRight"));
    
    ArrayList <String> leftHash = new ArrayList <String> ();
    leftHash.add ("n_regionkey");

    ArrayList <String> rightHash = new ArrayList <String> ();
    rightHash.add ("r_regionkey");
    
    String selection = "left.n_regionkey == right.r_regionkey";
                    
    HashMap <String, String> exprs = new HashMap <String, String> ();
    exprs.put ("s_nationkeyoutLeft", "left.s_nationkey");
    exprs.put ("l_discountoutLeft", "left.l_discount");
    exprs.put ("c_nationkeyoutLeft", "left.c_nationkey");
    exprs.put ("c_custkeyoutLeft", "left.c_custkey");
    exprs.put ("l_partkeyoutLeft", "left.l_partkey"); 
    
    exprs.put ("s_suppkeyoutLeft", "left.s_suppkey");
    exprs.put ("r_regionkeyoutRight", "right.r_regionkey");
    exprs.put ("o_orderdateoutLeft", "left.o_orderdate");
    exprs.put ("r_regionkeyoutLeft", "left.r_regionkey");
    
    exprs.put ("l_extendedpriceoutLeft", "left.l_extendedprice");
    exprs.put ("p_partkeyoutLeft", "left.p_partkey");
    exprs.put ("n_nationkeyoutLeft", "left.n_nationkey");
    exprs.put ("l_suppkeyoutLeft", "left.l_suppkey");
    exprs.put ("n_regionkeyoutLeft", "left.n_regionkey");
    
    exprs.put ("o_orderkeyoutLeft", "left.o_orderkey");
    exprs.put ("l_orderkeyoutLeft", "left.l_orderkey");
    exprs.put ("o_custkeyoutLeft", "left.o_custkey");
     
    // run the join
    try {
      Join foo = new Join (inAttsLeft, inAttsRight,outAtts, leftHash, rightHash, selection, exprs, 
                                "cn1.tbl", "r1out.tbl", "out.tbl", "g++", "cppDir/"); 
    } catch (Exception e) {
      throw new RuntimeException (e);
    }
    
  }
  
}