######################################################################
# Project           : JSONP Attack
#
# Program name      : mitigated.py
#
# Author            : Evan Kivolowitz
#
# Date created      : 02/19/2019
#
# Purpose           : Proxy server to restructure application.
#
# Credit            : 
#
# Use                                    Source
#
# Revision History  :
#
# Date        Author              Ref    Revision 
# 02/19/2019  Evan Kivolowitz      1     Created prototype of project.
# 02/28/2019  Evan Kivolowitz	   2	 Added a hint.
#
######################################################################
from flask import Flask, render_template
import requests
app = Flask(__name__)

# You may see that both render_template and requests are imported but not used *wink wink*.

@app.route("/")
def run():
	# put your code here to request data and send data to the front end
	return "So long, and thanks for all the fish."
if __name__ == "__main__":
    app.run(host="127.0.0.1", port=5002)
