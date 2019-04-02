######################################################################
# Project           : JSONP Attack
#
# Program name      : vulnerable.py
#
# Author            : Evan Kivolowitz
#
# Date created      : 02/19/2019
#
# Purpose           : Vulnerable server to JSONP.  
#
# Credit            : 
#
# Use                                    Source
#
# jsonp(f) function                      https://gist.github.com/farazdagi/1089923
#
# Revision History  :
#
# Date        Author              Ref    Revision 
# 02/19/2019  Evan Kivolowitz      1     Created prototype of project.
# 02/28/2019  Evan Kivolowitz      2     Updated headers.
#
######################################################################
from functools import wraps
from flask import request, current_app, jsonify, app, Flask

app = Flask(__name__)

'''
@farazdagi
'''
def jsonp(f):
    """Wraps output to JSONP"""
    @wraps(f)
    def decorated_function(*args, **kwargs):
        result = jsonify(f(*args, **kwargs))
        callback = request.args.get('callback', False)
        if callback:
            string = str(result.data)
            content = callback + '({})'.format(string[1:])
            return current_app.response_class(content,
                                              mimetype='application/javascript')
        else:
            return result
    return decorated_function

@app.route('/', methods=['GET'])
@jsonp
def test():
    return {'foo': 'bar', "moo" : "mar"}

if __name__ == "__main__":
    app.run("127.0.0.1", port=5000)
