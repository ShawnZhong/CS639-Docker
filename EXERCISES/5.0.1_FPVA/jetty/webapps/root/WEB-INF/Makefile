make:
	javac -cp .:../../../lib/* -d ./classes/ ./src/security/*/*

rebuild_db:
	@rm -f db/application.db
	@cd db && sqlite3 application.db < create_db.sql


