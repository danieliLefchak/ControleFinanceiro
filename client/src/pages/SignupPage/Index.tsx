import { ChangeEvent, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import AuthService from "../../service/AuthService";
import { Input } from "../../componentes/Input/Index";
import { ButtonWithProgress } from "../../componentes/ButtonWithProgress/Index";
import { ISignup } from "../../commons/interfaces";
import { Card, CardBody, CardHeader } from "@chakra-ui/react";

export function SignupPage() {
  const [form, setForm] = useState({
    username: "",
    email: "",
    telefone: "",
    cpf: "",
    password: "",
  });
  const [errors, setErrors] = useState({
    username: "",
    email: "",
    telefone: "",
    cpf: "",
    password: "",
  });
  const [apiError, setApiError] = useState(false);
  const [userSaved, setUserSaved] = useState(false);
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

    setErrors((previousErrors) => {
      return {
        ...previousErrors,
        [name]: undefined,
      };
    });
  };

  const onClickSignup = () => {
    setPendingApiCall(true);
    const user: ISignup = {
      username: form.username,
      email: form.email,
      telefone: form.telefone,
      cpf: form.cpf,
      password: form.password,
    };
    console.log(user);
    AuthService.signup(user)
      .then((response) => {
        console.log(response);

        setUserSaved(true);
        setApiError(false);
        navigate("/login");
      })
      .catch((responseError) => {
        setUserSaved(false);
        setApiError(true);
        console.log(responseError.response);
        if (
          responseError.response.data &&
          responseError.response.data.validationErrors
        ) {
          setErrors(responseError.response.data.validationErrors);
        }
      })
      .finally(() => {
        setPendingApiCall(false);
      });
    console.log("DEPOIS DO POST DO AXIOS");
  };

  return (
    <div className="container">
      <Card className="mb-3 mt-3 bg-light">
        <CardHeader className="fs-2 text-center fw-bold">
          User Signup
        </CardHeader>

        <CardBody>
          <div className="col-12 mb-3">
            <Input
              label="Informe seu username"
              className="form-control"
              type="text"
              placeholder="Informe o seu username"
              name="username"
              onChange={onChange}
              value={form.username}
              hasError={errors.username ? true : false}
              error={errors.username}
            />
          </div>

          <div className="col-12 mb-3">
            <Input
              label="Informe seu email"
              className="form-control"
              type="email"
              placeholder="Informe o seu email"
              name="email"
              onChange={onChange}
              value={form.email}
              hasError={errors.email ? true : false}
              error={errors.email}
            />
          </div>

          <div className="col-12 mb-3">
            <Input
              label="Informe seu telefone"
              className="form-control"
              type="text"
              placeholder="Informe o seu telefone"
              name="telefone"
              onChange={onChange}
              value={form.telefone}
              hasError={errors.telefone ? true : false}
              error={errors.telefone}
            />
          </div>

          <div className="col-12 mb-3">
            <Input
              label="Informe seu cpf"
              className="form-control"
              type="text"
              placeholder="Informe o seu cpf"
              name="cpf"
              onChange={onChange}
              value={form.cpf}
              hasError={errors.cpf ? true : false}
              error={errors.cpf}
            />
          </div>

          <div className="col-12 mb-3">
            <Input
              label="Informe a sua senha"
              className="form-control"
              type="password"
              placeholder="Informe a sua senha"
              name="password"
              onChange={onChange}
              value={form.password}
              hasError={errors.password ? true : false}
              error={errors.password}
            />
          </div>
          <div className="text-center">
            <ButtonWithProgress
              onClick={onClickSignup}
              className="btn btn-primary"
              disabled={pendingApiCall}
              pendingApiCall={pendingApiCall}
              text="Cadastrar"
            />
            {userSaved && (
              <div className="alert alert-success">
                Usuário cadastrado com sucesso!
              </div>
            )}
            {apiError && (
              <div className="alert alert-danger">
                Falha ao cadastrar o usuário.
              </div>
            )}
          </div>
        </CardBody>
      </Card>

      <div className="text-center">
        <span>já possui cadastro? </span>
        <Link to="/">Autenticar-se</Link>
      </div>
    </div>
  );
}
