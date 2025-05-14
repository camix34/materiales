import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from './auth.service';
import { map } from 'rxjs/operators';

export const guardGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  // Verificamos si el usuario tiene el username en localStorage
  return authService.getAuthToken().pipe(
    map(isAuthenticated => {
      if (!isAuthenticated) {
        // Si no está autenticado (sin username), redirigimos al login
        router.navigate(['/login']);
        return false;
      }
      
      return true; // Si está autenticado, permitimos el acceso
    })
  );
}