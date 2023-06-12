import { Link } from "react-router-dom";

export function Footer() {
  return (
    <div className="shadow-lg bg-light p-2 mt-4">
      <div className="container">
        <div className="row">
          <div className="col-lg-4 mb-3">
            <Link
              className="d-inline-flex align-items-center mb-2 fs-5"
              to="/Home"
            >
              Financeiro
            </Link>

            <ul className="list-unstyled small text-muted">
              <li className="mb-2">
                Aplicação WEB para controle financeiro pessoal
              </li>
              <li className="mb-2">Trabalho final de programação web</li>
              <li className="mb-2">
                Desenvolvido pela Acadêmica Danieli Maria Lefchak
              </li>
            </ul>
          </div>
          <div className="col-8 col-lg-4 offset-lg-2 mb-3">
            <h5>Links</h5>
            <ul className="list-unstyled">
              <li className="mb-2">
                <Link to="/">Home</Link>
              </li>
              <li className="mb-2">
                <Link to="/contas">Contas</Link>
              </li>
              <li className="mb-2">
                <Link to="/">Home</Link>
              </li>
              <li className="mb-2">
                <Link to="/">Home</Link>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
}
