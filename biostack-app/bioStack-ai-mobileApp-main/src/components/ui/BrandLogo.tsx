import logoNero from '../../img/logo/logoNero.png';
import logoBianco from '../../img/logo/logoBianco.png';

interface BrandLogoProps {
  className?: string;
  alt?: string;
}

export function BrandLogo({ className = '', alt = 'BioStack AI logo' }: BrandLogoProps) {
  return (
    <div className={className} aria-label={alt}>
      <img src={logoNero} alt={alt} className="block h-auto w-full dark:hidden" />
      <img src={logoBianco} alt={alt} className="hidden h-auto w-full dark:block" />
    </div>
  );
}
