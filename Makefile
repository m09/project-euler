check:
	black --check project_euler
	mypy project_euler
	flake8 --count project_euler
	pylint project_euler
