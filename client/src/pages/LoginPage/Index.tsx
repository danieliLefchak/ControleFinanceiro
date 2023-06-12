import { ChangeEvent, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { IUserLogin } from "../../commons/interfaces";
import AuthService from "../../service/AuthService";
import { ButtonWithProgress } from "../../componentes/ButtonWithProgress/Index";
import { Card, CardBody, CardHeader } from "@chakra-ui/react";

export function LoginPage() {
  const [form, setForm] = useState({
    username: "",
    password: "",
  });
  const [apiError, setApiError] = useState(false);
  const [userAuthenticated, setUserAuthenticated] = useState(false);
  const [pendingApiCall, setPendingApiCall] = useState(false);
  const navigate = useNavigate();

  const onChange = (event: ChangeEvent<HTMLInputElement>) => {
    const { value, name } = event.target;
    setForm((previousForm) => {
      return {
        ...previousForm,
        [name]: value,
      };
    });
    setApiError(false);
  };

  const onClickLogin = () => {
    const user: IUserLogin = {
      username: form.username,
      password: form.password,
    };

    setPendingApiCall(true);

    AuthService.login(user)
      .then((response) => {
        setUserAuthenticated(true);
        setApiError(false);
        localStorage.setItem("token", JSON.stringify(response.data.token));
        navigate("/home");
      })
      .catch((responseError) => {
        setUserAuthenticated(false);
        setApiError(true);
      })
      .finally(() => {
        setPendingApiCall(false);
      });
  };

  return (
    <div className="container">
      <Card className="mb-3 mt-3 bg-light">
        <CardHeader className="fs-2 text-center fw-bold">Login</CardHeader>

        <CardBody>
          <div className="col-12 mb-3">
            <label>Informe seu username</label>
            <input
              className={apiError ? "form-control is-invalid" : "form-control"}
              type="text"
              placeholder="Informe o seu username"
              name="username"
              onChange={onChange}
              value={form.username}
            />
          </div>
          <div className="col-12 mb-3">
            <label>Informe sua senha</label>
            <input
              className={apiError ? "form-control is-invalid" : "form-control"}
              type="password"
              placeholder="Informe a sua senha"
              name="password"
              onChange={onChange}
              value={form.password}
            />
          </div>
          <div className="text-center">
            <ButtonWithProgress
              onClick={onClickLogin}
              className="btn btn-primary"
              disabled={pendingApiCall}
              pendingApiCall={pendingApiCall}
              text="Entrar"
            />
            {userAuthenticated && (
              <div className="alert alert-success">
                Usuário autenticado com sucesso!
              </div>
            )}
            {apiError && (
              <div className="alert alert-danger">
                Falha ao autenticar o usuário.
              </div>
            )}
          </div>
        </CardBody>
      </Card>
      <div className="text-center">
        <span>não possui cadastro? </span>
        <Link to="/signup">Cadastrar-se</Link>
      </div>
    </div>
  );
}
