-- Query 8
SELECT AL4.REVN_YR, AL4.REVN_MO, AL3.SRVPLN_ID,
 AL3.SRVPLN_NM, AL2.GEOG_CNTY_CD, SUM(AL5.RSD_SVC_BILG_AMT)
FROM
 PGE.CUST_SERV_ACCT AL1,
 PGE.GEOG AL2,
 PGE.SERV_PLAN AL3,
 PGE.REVN_PRD AL4,
 PGE.REVN_DTL_RAND AL5
 WHERE (AL5.REVN_YR_MO=AL4.REVN_YR_MO
 AND AL2.GEOG_KEY=AL5.GEOG_KEY
 AND AL3.SRVPLN_KEY=AL5.SRVPLN_KEY
 AND AL1.CUST_SERV_ACCT_KEY=AL5.CUST_SERV_ACCT_KEY)
GROUP BY AL4.REVN_YR, AL4.REVN_MO, AL3.SRVPLN_ID,
 AL3.SRVPLN_NM, AL2.GEOG_CNTY_CD
ORDER BY  5, 1, 2, 3, 4, 6
;