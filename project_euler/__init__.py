from importlib import import_module, invalidate_caches as importlib_invalidate_caches
from logging import basicConfig, INFO
from pkgutil import walk_packages

from rich.logging import RichHandler
from typer import Typer


app = Typer(help="Project Euler exercises.")


def main() -> None:
    basicConfig(
        level=INFO,
        format="%(message)s",
        datefmt="%H:%M:%S",
        handlers=[RichHandler(rich_tracebacks=True)],
    )
    _import_module_and_submodules(__name__)
    app()


def _import_module_and_submodules(package_name: str) -> None:
    """
    From https://github.com/allenai/allennlp/blob/master/allennlp/common/util.py
    """
    importlib_invalidate_caches()

    module = import_module(package_name)
    path = getattr(module, "__path__", [])
    path_string = "" if not path else path[0]

    for module_finder, name, _ in walk_packages(path):
        if path_string and module_finder.path != path_string:
            continue
        subpackage = f"{package_name}.{name}"
        _import_module_and_submodules(subpackage)
