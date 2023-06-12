import { Link, NavLink } from "react-router-dom";
import logo from "../../assets/utfpr-logo.png";
import AuthService from "../../service/AuthService";
import { Button } from "@chakra-ui/react";

export function NavBar() {
  const onClickLogout = () => {
    AuthService.logout();
    window.location.reload();
  };

  return (
    <div className="bg-white shadow-sm mb-2">
      <div className="container">
        <nav className="navbar navbar-expand">
          <Link to="/" className="navbar-brand">
            <img src={logo} width="60" alt="UTFPR" />
          </Link>

          <ul className="navbar-nav me-auto mb-2 mb-md-0">
            <li className="nav-item">
              <NavLink
                to="/"
                className={(navData) =>
                  navData.isActive ? "nav-link active" : "nav-link"
                }
              >
                Home
              </NavLink>
            </li>

            <li className="nav-item">
              <NavLink
                to="/contas"
                className={(navData) =>
                  navData.isActive ? "nav-link active" : "nav-link"
                }
              >
                Contas
              </NavLink>
            </li>

            <li className="nav-item">
              <NavLink
                to="/movimentacoes/new"
                className={(navData) =>
                  navData.isActive ? "nav-link active" : "nav-link"
                }
              >
                Movimentação
              </NavLink>
            </li>

            <li className="nav-item">
              <NavLink
                to="/transferencias/new"
                className={(navData) =>
                  navData.isActive ? "nav-link active" : "nav-link"
                }
              >
                Transferência
              </NavLink>
            </li>

            <li className="nav-item">
              <Button
                className=""
                colorScheme="gray"
                variant="ghost"
                onClick={onClickLogout}
              >
                Sair
              </Button>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  );
}
