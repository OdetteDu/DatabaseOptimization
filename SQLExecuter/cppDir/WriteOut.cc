Int l_orderkeyoutLeft;
Int l_suppkeyoutLeft;
Int l_orderkeyoutRight;
Int l_suppkeyoutRight;

l_orderkeyoutLeft = left.l_orderkey;
l_suppkeyoutLeft = left.l_suppkey;
l_orderkeyoutRight = right.l_orderkey;
l_suppkeyoutRight = right.l_suppkey;

l_orderkeyoutLeft.WriteOut (toMe);
l_suppkeyoutLeft.WriteOut (toMe);
l_orderkeyoutRight.WriteOut (toMe);
l_suppkeyoutRight.WriteOut (toMe);
