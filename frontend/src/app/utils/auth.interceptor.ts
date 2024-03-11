import {Injectable} from "@angular/core";
import {HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    let requestWithHeader = req.clone({
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest'),
      withCredentials: true,
    });

    if (req.headers.has('skipIntercept')) {
      requestWithHeader = req.clone({
        headers: req.headers.delete('skipIntercept'),
      });
    }

    return next.handle(requestWithHeader);
  }
}
