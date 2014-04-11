aggMe.ccccccc_o_orderdate = o_orderdate;
aggMe.att0 = (Str("date was ") + o_orderdate);
aggMe.att1 = aggMe.att1 + (l_extendedprice * (Int(1) - l_discount));
aggMe.ccccccc_count = aggMe.ccccccc_count + Int (1);
