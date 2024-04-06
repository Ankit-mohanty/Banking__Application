import { HttpInterceptorFn } from '@angular/common/http';

export const requestInterceptor: HttpInterceptorFn = (req, next) => {
  if (req.headers.get("noauth")) {
    return next(req);
  }

  const token = localStorage.getItem("token");
  if (!token)
    location.href = "/signin"
  
  const cloneReq = req.clone({
    setHeaders: {
      "name": "smruti",
      "Authorization": "Bearer " + token
    }
  });

  return next(cloneReq);
};
