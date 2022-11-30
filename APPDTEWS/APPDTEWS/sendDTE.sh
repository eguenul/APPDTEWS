curl -X 'POST' \
'https://pangal.sii.cl/recursos/v1/boleta.electronica.envio' \
-H 'accept: application/json' \
-H 'User-Agent: Mozilla/4.0 ( compatible; PROG 1.0; Windows NT)' \
-H 'Cookie: TOKEN=CWJLRIC5FWKBI' \
-H 'Content-Type: multipart/form-data' \
-F 'rutSender=1396841' \
-F 'dvSender=8' \
-F 'rutCompany=76040308' \
-F 'dvCompany=3' \



-F 'archivo=@ENVDTE76040308F1T39.xml;type=text/xml'
