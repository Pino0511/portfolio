import { useMemo, useState } from 'react';
import { ArrowLeft, BookOpen, Clock } from 'lucide-react';
import { Screen } from '../ui/Screen';
import { Card } from '../ui/Card';
import { Chip } from '../ui/Chip';
import { WIKI_ARTICLES, WIKI_CATEGORIES, type WikiArticle, type WikiCategory } from '../../lib/biowiki';
import { useApp } from '../../context/AppContext';

export function BioWiki() {
  const { setOverlay, setNavTab } = useApp();
  const [category, setCategory] = useState<WikiCategory | 'all'>('all');
  const [article, setArticle] = useState<WikiArticle | null>(null);

  const list = useMemo(
    () => (category === 'all' ? WIKI_ARTICLES : WIKI_ARTICLES.filter((a) => a.category === category)),
    [category],
  );

  const close = () => {
    setOverlay('none');
  };

  return (
    <div className="fixed inset-0 z-50 mx-auto max-w-md bg-canvas dark:bg-stone-950">
      <Screen className="h-full overflow-y-auto pb-8">
        <header className="safe-top sticky top-0 z-10 flex items-center gap-3 border-b border-stone-200 bg-canvas px-4 py-3 backdrop-blur shadow-sm dark:border-stone-800 dark:bg-stone-950/95 dark:shadow-2xl dark:shadow-black/35">
          <button
            type="button"
            onClick={() => (article ? setArticle(null) : close())}
            className="flex h-11 w-11 items-center justify-center rounded-2xl bg-stone-100 dark:bg-stone-900"
            aria-label="Indietro"
          >
            <ArrowLeft className="h-5 w-5" />
          </button>
          <div>
            <h1 className="text-lg font-semibold text-stone-900 dark:text-stone-100">
              {article ? article.title : 'BioWiki'}
            </h1>
            <p className="text-xs text-stone-500">Academy & knowledge base</p>
          </div>
        </header>

        {!article ? (
          <div className="space-y-4 px-5 py-5">
            <Card>
              <div className="flex gap-3">
                <BookOpen className="h-5 w-5 text-forest-700 dark:text-moss-400" />
                <p className="text-sm text-stone-600 dark:text-stone-300">
                  Articoli evidence-informed su postura, glow, longevity e sonno. Non sono diagnosi mediche.
                </p>
              </div>
            </Card>

            <div className="flex gap-2 overflow-x-auto pb-1">
              <Chip active={category === 'all'} onClick={() => setCategory('all')}>
                Tutti
              </Chip>
              {WIKI_CATEGORIES.map((c) => (
                <Chip key={c.id} active={category === c.id} onClick={() => setCategory(c.id)}>
                  {c.label}
                </Chip>
              ))}
            </div>

            <div className="space-y-3">
              {list.map((a) => (
                <Card key={a.id} onClick={() => setArticle(a)}>
                  <p className="text-[11px] font-semibold uppercase tracking-wide text-forest-700 dark:text-moss-400">
                    {WIKI_CATEGORIES.find((c) => c.id === a.category)?.label}
                  </p>
                  <h2 className="mt-1 font-semibold text-stone-900 dark:text-stone-100">{a.title}</h2>
                  <p className="mt-1 text-sm text-stone-600 dark:text-stone-400">{a.summary}</p>
                  <p className="mt-3 inline-flex items-center gap-1 text-xs text-stone-500">
                    <Clock className="h-3.5 w-3.5" /> {a.readMinutes} min di lettura
                  </p>
                </Card>
              ))}
            </div>
          </div>
        ) : (
          <article className="space-y-5 px-5 py-5 animate-fade-in">
            <div className="flex items-center gap-2 text-xs text-stone-500">
              <Clock className="h-3.5 w-3.5" /> {article.readMinutes} min ·{' '}
              {WIKI_CATEGORIES.find((c) => c.id === article.category)?.label}
            </div>
            <p className="text-base leading-relaxed text-stone-700 dark:text-stone-300">{article.summary}</p>
            {article.sections.map((s) => (
              <section key={s.heading}>
                <h3 className="text-base font-semibold text-stone-900 dark:text-stone-100">{s.heading}</h3>
                <p className="mt-2 text-sm leading-relaxed text-stone-600 dark:text-stone-400">{s.body}</p>
              </section>
            ))}
            <Card className="border-forest-600/30 bg-forest-50 dark:bg-stone-900 dark:border-moss-400/20">
              <p className="text-sm font-semibold text-stone-900 dark:text-stone-100">
                Prodotti / abitudini correlate dallo Stack
              </p>
              <ul className="mt-2 space-y-1 text-sm text-stone-700 dark:text-stone-300">
                {article.relatedStackNames.map((n) => (
                  <li key={n}>· {n}</li>
                ))}
              </ul>
              <button
                type="button"
                className="mt-4 text-sm font-semibold text-forest-700 dark:text-moss-400"
                onClick={() => {
                  close();
                  setNavTab('stack');
                }}
              >
                Apri Stack →
              </button>
            </Card>
          </article>
        )}
      </Screen>
    </div>
  );
}
